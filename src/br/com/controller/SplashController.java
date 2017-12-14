package br.com.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.view.Janelas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashController implements Initializable{
	@FXML
	private AnchorPane paneSplash;
	@FXML
	private ImageView imgLogo;
	@FXML
	private ProgressIndicator progIndicator;
	
	private int time = 4000;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Timeline timeSplash = new Timeline(new KeyFrame(
				Duration.millis(time),
				f ->  {
					closeSplash();
				}));
		
		timeSplash.play();
		
		progIndicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
		progIndicator.setStyle("-fx-progress-color: #61A218");			
	}

	private void closeSplash() {
		Stage stage = (Stage) paneSplash.getScene().getWindow();
		stage.close();
		start();
	}

	private void start() {
		Janelas login = new Janelas();
		Stage stage = new Stage();
		login.abrirJanelas("Login.fxml", stage, "Entre ou cadastre-se");	
	}

}
