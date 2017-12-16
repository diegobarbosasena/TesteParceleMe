package br.com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.ajudantes.Alert;
import br.com.ajudantes.Masks;
import br.com.dao.PessoaDao;
import br.com.model.Pessoa;
import br.com.view.Janelas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FuncaoController implements Initializable {
	
	@FXML
	private AnchorPane paneFuncao;
	@FXML
	private Label lblNome;
	@FXML
	private ImageView imgFoto ;
	@FXML
	private Label lblCaminho;
	@FXML
	private TextField txtNome;
	@FXML
	private Label lblSobrenome;
	@FXML
	private TextField txtSobrenome;
	@FXML
	private Label lblTelefone;
	@FXML
	private TextField txtTelefone;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnProcurar;
	@FXML
	private Button btnConcluido;
	
	static String funcao = null;
	
	private FileChooser fileChooser;
	private File file;
	private Image image;
	private FileInputStream fis;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Masks.mascaraCelular(txtTelefone);
		
		btnProcurar.setOnAction(c -> search());
		
		switch (funcao) {
		
			case "cadastar": keyEnterInsert(); break;
			
			case "atualizar": showImage(); populateTextField(); keyEnterUpdate(); break;
			
			case "visualizar": showImage(); viewPessoa(); break; 
			
			default: break;
		}
		
		btnConcluido.setOnAction(v -> completed());
		
		btnConcluido.setOnKeyPressed(o -> {
			if (o.getCode() == KeyCode.ENTER) {
				completed();
			}
		});
	}
	
	private void search() {
		
		imgFoto.setImage(null);
		
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("imagens", "*.jpg", "*.png"));
		
		file = fileChooser.showOpenDialog(paneFuncao.getScene().getWindow());
		
		if (file != null) {
			
			lblCaminho.setText(file.getAbsolutePath());
			image = new Image(file.toURI().toString());
			
			imgFoto.setImage(image);
			
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void insertPessoa(){
		
		if (txtNome.getText().isEmpty() || txtSobrenome.getText().isEmpty() || txtTelefone.getText().isEmpty() || file == null) {
			
			Alert.showError("Preencha todos os campos");	
		
		} else {
		
			Pessoa pessoa = new Pessoa();
			
			pessoa.setNome(txtNome.getText());
			pessoa.setSobrenome(txtSobrenome.getText());
			pessoa.setTelefone(txtTelefone.getText());
			
			PessoaDao pessoaDao = new PessoaDao();
			pessoaDao.insertPessoa(pessoa, fis);
			
			Alert.showConfirmation("Item cadastrado com sucesso");
			
			if (Alert.alert_true) {
				completed();
			} 
		}	
	}
	
	private void populateTextField() {
		
		List<Pessoa> lstPessoa = new ArrayList<>();
		
		PessoaDao pessoaDao = new PessoaDao();
		
		lstPessoa = pessoaDao.getPessoa(LayoutController.pessoa_id);
		
		for (Pessoa pessoa : lstPessoa) {
			
			txtNome.setText(pessoa.getNome());
			txtSobrenome.setText(pessoa.getSobrenome());
			txtTelefone.setText(pessoa.getTelefone());	
		}
	}
	
	private void updatePessoa() {
		
		if (txtNome.getText().isEmpty() || txtSobrenome.getText().isEmpty() || txtTelefone.getText().isEmpty() || file == null) {
			
			Alert.showError("Preencha todos os campos");
			
		} else {
			Pessoa pessoa = new Pessoa();
			
			pessoa.setId(LayoutController.pessoa_id);
			pessoa.setNome(txtNome.getText());
			pessoa.setSobrenome(txtSobrenome.getText());
			pessoa.setTelefone(txtTelefone.getText());
			
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			PessoaDao pessoaDao = new PessoaDao();
			
			pessoaDao.updatePessoa(pessoa, fis);
			
			clearTextField();
			
			Alert.showConfirmation("Item atualizado com sucesso");
			
			if (Alert.alert_true) {
				completed();
			} 
			funcao = "cadastar";
		}		
	}
	
	private void viewPessoa(){
		
		txtNome.setDisable(true);
		txtSobrenome.setDisable(true);
		txtTelefone.setDisable(true);
		
		btnProcurar.setVisible(false);
		
		btnCadastrar.setVisible(false);
		
		btnConcluido.setText("Concluído");
		
		List<Pessoa> lstPessoa = new ArrayList<>();
		
		PessoaDao pessoaDao = new PessoaDao();
		
		lstPessoa = pessoaDao.getPessoa(LayoutController.pessoa_id);
		
		for (Pessoa pessoa : lstPessoa) {
			
			txtNome.setText(pessoa.getNome());
			txtSobrenome.setText(pessoa.getSobrenome());
			txtTelefone.setText(pessoa.getTelefone());
		}
		
		funcao = "cadastar";
	}
	
	private void showImage() {
		
		PessoaDao pessoaDao = new PessoaDao();
		
		image = pessoaDao.getFoto(LayoutController.pessoa_id);

		imgFoto.setImage(image);
	}
	
	private void clearTextField() {
		txtNome.clear();
		txtSobrenome.clear();
		txtTelefone.clear();
	}
	
	private void keyEnterInsert() {
		
		btnCadastrar.setText("Cadastar");
		
		btnCadastrar.setOnAction(p -> insertPessoa());
		
		txtNome.setOnKeyPressed(l -> {
			if (l.getCode() == KeyCode.ENTER) {
				insertPessoa();
			}
		});
		
		txtSobrenome.setOnKeyPressed(l -> {
			if (l.getCode() == KeyCode.ENTER) {
				insertPessoa();
			}
		});
		
		txtTelefone.setOnKeyPressed(l -> {
			if (l.getCode() == KeyCode.ENTER) {
				insertPessoa();
			}
		});
		
		btnCadastrar.setOnKeyPressed(l -> {
			if (l.getCode() == KeyCode.ENTER) {
				insertPessoa();
			}
		});
	}
	
	private void keyEnterUpdate() {
		
		btnCadastrar.setText("Atualizar");
		
		btnCadastrar.setOnAction(a -> updatePessoa());
		
		txtNome.setOnKeyPressed(l -> {
			if (l.getCode() == KeyCode.ENTER) {
				updatePessoa();
			}
		});
		
		txtSobrenome.setOnKeyPressed(l -> {
			if (l.getCode() == KeyCode.ENTER) {
				updatePessoa();
			}
		});
		
		txtTelefone.setOnKeyPressed(l -> {
			if (l.getCode() == KeyCode.ENTER) {
				updatePessoa();
			}
		});
		
		btnCadastrar.setOnKeyPressed(l -> {
			if (l.getCode() == KeyCode.ENTER) {
				updatePessoa();
			}
		});
	}
	
	private void completed() {
		
		Stage stageCadastar = (Stage) paneFuncao.getScene().getWindow();
		stageCadastar.close();
		
		Janelas janelas = new Janelas();
		Stage stage = new Stage();
		janelas.abrirJanelas("Layout.fxml", stage, "Tela principal");
	}
	
}
