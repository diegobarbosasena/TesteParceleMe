package br.com.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.dao.PessoaDao;
import br.com.model.Pessoa;
import br.com.view.Janelas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VisualizarController implements Initializable {
	@FXML
	private AnchorPane paneVisualizar;
	@FXML
	private AnchorPane paneFoto;
	@FXML
	private ImageView imgFoto;
	@FXML
	private Label lblNome;
	@FXML
	private Label lblSobrenome;
	@FXML
	private Label lblTelefone;
	@FXML
	private Button btnConcluido;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnConcluido.setOnAction(p -> completed());
		
		List<Pessoa> lstPessoa = new ArrayList<>();
		
		PessoaDao pessoaDao = new PessoaDao();
		
		lstPessoa = pessoaDao.getPessoa(LayoutController.pessoa_id);
		
		for (Pessoa pessoa : lstPessoa) {
			
			lblNome.setText(pessoa.getNome());
			lblSobrenome.setText(pessoa.getSobrenome());
			lblTelefone.setText(pessoa.getTelefone());
		}
	}
	
	private void completed() {
		
		Stage stageVisualizar = (Stage) paneVisualizar.getScene().getWindow();
		stageVisualizar.close();
		
		Janelas janelas = new Janelas();
		Stage stage = new Stage();
		janelas.abrirJanelas("Layout.fxml", stage, "Tela principal");

	}

}
