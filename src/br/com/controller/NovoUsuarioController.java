package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.ajudantes.Alert;
import br.com.dao.UsuarioDao;
import br.com.model.Usuario;
import br.com.view.Janelas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NovoUsuarioController implements Initializable {
	
	@FXML
	private AnchorPane paneLogin;
	@FXML
	private ImageView imgLogo;
	@FXML
	private Label lblUsuario;
	@FXML
	private TextField txtUsuario;
	@FXML
	private Label lblSenha;
	@FXML
	private PasswordField txtSenha;
	@FXML
	private Button btnCadastarUsuario;
	@FXML
	private Button btnVoltar;
	@FXML
	private ProgressIndicator progressIdicatorUsuario;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		progressIdicatorUsuario.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
		progressIdicatorUsuario.setStyle("-fx-progress-color: #61A218");
		
		btnVoltar.setOnAction(b -> back());
		btnCadastarUsuario.setOnAction(c -> newUser());	
	}

	private void newUser() {
		
		if (txtUsuario.getText().isEmpty() || txtSenha.getText().isEmpty()) {
			Alert.showError("Preencha todos os campos");		
			
		} else {
			
			Usuario usuario = new Usuario();
			
			usuario.setUsuario(txtUsuario.getText());
			usuario.setSenha(txtSenha.getText());
			
			UsuarioDao usuarioDao = new UsuarioDao();
			usuarioDao.insertUsuario(usuario);
			
			Alert.showConfirmation("Usuario cadastrado com sucesso");
			
			clearText();

			if (Alert.alert_true) {
				login();
			} else {
				newUser();
			}
		}
	}

	private void login() {
		Stage stageLogin = (Stage) btnCadastarUsuario.getScene().getWindow();
		stageLogin.close();
		
		Janelas login = new Janelas();
		Stage stage = new Stage();
		login.abrirJanelas("Login.fxml", stage, "Entre ou cadastre-se");	
	}
	
	private void back() {
		
		Stage stageNewUser = (Stage) btnVoltar.getScene().getWindow();
		stageNewUser.close();
		
		Janelas login = new Janelas();
		Stage stage = new Stage();
		login.abrirJanelas("Login.fxml", stage, "Entre ou cadastre-se");
		
		clearText();
	}

	private void clearText() {
		txtSenha.clear();
		txtUsuario.clear();
	}
	
}
