package br.com.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Janelas {

	public void abrirJanelas(String file, Stage primaryStage, String title) {
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(file));
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle(title);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void openSplash(String file, Stage stage) {
		
		Parent parent;
		try {	
			parent = FXMLLoader.load(getClass().getResource(file));
		
			stage.setScene(new Scene(parent));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
