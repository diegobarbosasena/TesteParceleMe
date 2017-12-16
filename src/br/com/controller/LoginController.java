package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.ajudantes.Alert;
import br.com.dao.UsuarioDao;
import br.com.view.Janelas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
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
	private Button btnNovoUsuario;
	@FXML
	private Button btnEntrar;
	
	public static String usuario_login;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnNovoUsuario.setOnAction(i -> newUser());
		
		btnEntrar.setOnAction(v -> login());
		
		btnEntrar.setOnKeyPressed(i -> {
			if (i.getCode() == KeyCode.ENTER) {
				login();
			}
		});
		
		txtUsuario.setOnKeyPressed(o -> {
			if (o.getCode() == KeyCode.ENTER) {
				login();
			}
		});
		
		txtSenha.setOnKeyPressed(l -> {
			if (l.getCode() == KeyCode.ENTER) {
				login();
			}
		});
	}
	
	private void newUser() {
		
		Stage login = (Stage) paneLogin.getScene().getWindow();
		login.close();
		
		Janelas newUser = new Janelas();
		Stage stage = new Stage();
		newUser.abrirJanelas("NovoUsuario.fxml", stage, "Cadastre-se");
	}

	private void login() {
		
		if (txtSenha.getText().isEmpty() || txtUsuario.getText().isEmpty()) {
			
			Alert.showError("Preencha Todos os campos");		
		} else {
			
			UsuarioDao usuarioDao = new UsuarioDao();
			
			if (usuarioDao.checkLogin(txtUsuario.getText(), txtSenha.getText())) {
				
				usuario_login = txtUsuario.getText();
				
				clearLogin();
				
				Stage stageLogin = (Stage) paneLogin.getScene().getWindow();
				stageLogin.close();
									
				Janelas janela = new Janelas();
				Stage stage = new Stage();
				janela.abrirJanelas("Layout.fxml", stage, "Bem vindo");
				
			} else {
				Alert.showError("Usuário ou senha está errado");
			}	
		}
	}
	
	private void clearLogin() {
		txtUsuario.clear();
		txtSenha.clear();
	
	}

}
