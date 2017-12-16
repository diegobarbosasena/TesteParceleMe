package br.com.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import br.com.ajudantes.Alert;
import br.com.dao.PessoaDao;
import br.com.model.Pessoa;
import br.com.view.Janelas;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LayoutController implements Initializable{
	@FXML
	private AnchorPane paneLayout;
	@FXML
	private Label lblUsuario;
	@FXML
	private Label lblHoraData;
	@FXML
	private ImageView imgLogo;
	@FXML
	private Button btnEncerrar;
	@FXML
	private Button btnVisualizar;
	@FXML
	private Button btnNovo;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnDeletar;
	@FXML
	private TableView <Pessoa> tblPessoa;
	@FXML
	private TableColumn <Pessoa, Long> tcId;
	@FXML
	private TableColumn <Pessoa, String> tcNome;
	@FXML
	private TableColumn <Pessoa, String> tcSobrenome;
	@FXML
	private TableColumn <Pessoa, String> tcTelefone;
	
	public static Long pessoa_id;
	
	String data;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		dateTime();
		populatePessoa();

		lblUsuario.setText(LoginController.usuario_login);
		
		btnEncerrar.setOnAction(f -> closeSession());
		
		btnVisualizar.setOnAction(v -> viewPessoa());
		btnNovo.setOnAction(d -> createPessoa());
		btnDeletar.setOnAction(c -> deletePessoa());
		
		btnEditar.setOnAction(f -> updatePessoa());
	}
	
	private void populatePessoa() {
		
		tcId.setCellValueFactory(new PropertyValueFactory<Pessoa, Long>("id"));
		tcNome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("nome"));
		tcSobrenome.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("sobrenome"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<Pessoa, String>("telefone"));
		
		PessoaDao pessoaDao = new PessoaDao();
		
		List<Pessoa> lst = pessoaDao.getPessoas();
		
		tblPessoa.getItems().clear();
		tblPessoa.getItems().addAll(lst);
	}
	
	private void deletePessoa() {
		
		Pessoa pessoa = tblPessoa.getSelectionModel().getSelectedItem();
		
		if(pessoa != null) {
			
			pessoa.getId();
			
			PessoaDao pessoaDao = new PessoaDao();
			pessoaDao.deletePessoa(pessoa);
			
			Alert.showInformation("SUCESSO", "Item excluído");
		}
		else {
			Alert.showError("Nenhum item selecionado");
		}
		populatePessoa();
	}

	private void createPessoa() {
		
		CadastrarController.funcao = "cadastar" ;
		
		Stage stageLayout = (Stage) paneLayout.getScene().getWindow();
		stageLayout.close();
		
		Janelas janelas = new Janelas();
		Stage stage = new Stage();
		janelas.abrirJanelas("Cadastrar.fxml", stage, "Criar nova pessoa");
	}
	
	private void updatePessoa() {
		
		CadastrarController.funcao = "atualizar";
		
		Pessoa pessoa = tblPessoa.getSelectionModel().getSelectedItem();
		
		if (pessoa == null) {
			
			Alert.showError("Nenhum item selecionado");
		} else {
			
			pessoa_id = pessoa.getId();
			
			Stage stagePrincipal = (Stage) paneLayout.getScene().getWindow();
			stagePrincipal.close();
			
			Janelas janelas = new Janelas();
			Stage stage = new Stage();
			janelas.abrirJanelas("Cadastrar.fxml", stage, "Atualizar dados");		
		}
	}
	
	private void viewPessoa() {
		
		CadastrarController.funcao = "visualizar";
		
		Pessoa pessoa = tblPessoa.getSelectionModel().getSelectedItem();
		
		if (pessoa == null) {
			
			Alert.showError("Nenhum item selecionado");
					
		} else {
			
			pessoa_id = pessoa.getId();
			
			Stage stageLayout = (Stage) paneLayout.getScene().getWindow();
			stageLayout.close();
			
			Janelas janelas = new Janelas();
			Stage stage = new Stage();
			janelas.abrirJanelas("Cadastrar.fxml", stage, "Visualizar pessoa");
		}
	}
	
 	private void closeSession() {
		
		Stage stagePrincipal = (Stage) paneLayout.getScene().getWindow();
		stagePrincipal.close();
		
		Janelas janelaLogin = new Janelas();
		Stage stage = new Stage();
		janelaLogin.abrirJanelas("login.fxml", stage, "Login");

	}
 	
 	private void dateTime() {
		
 		SimpleDateFormat date = new SimpleDateFormat("dd/MM/YYYY");
 		data = date.format(new Date());
 		
 		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while (true) {
					
					final SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
					
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							
							lblHoraData.setText(data + " - " + time.format(new Date()));
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						
						break;
					}
				}	
			}
		});
 		thread.start();
	}
}
