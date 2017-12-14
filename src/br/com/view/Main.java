package br.com.view;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		Janelas splash = new Janelas();
		splash.openSplash("Splash.fxml", primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
		System.exit(0);
	}
}
