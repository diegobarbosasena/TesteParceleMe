package br.com.ajudantes;

import javafx.scene.control.Alert.AlertType;

public class Alert {

	public static boolean alert_true = false;
	
	public static void showWarning(String title, String mesage) {
		
		javafx.scene.control.Alert alert = new javafx.scene.control.Alert(AlertType.WARNING);
		alert.setHeaderText("OPS!");
		alert.setContentText(mesage);
		alert.show();
	}
	
	public static void showInformation(String title, String mesage) {
		
		javafx.scene.control.Alert alert = new javafx.scene.control.Alert(AlertType.INFORMATION);
		alert.setHeaderText("SUCESSO!");
		alert.setContentText(mesage);
		alert.show();
	}
	
	public static void showError(String mesage) {
		
		javafx.scene.control.Alert alert = new javafx.scene.control.Alert(AlertType.ERROR);
		alert.setContentText(mesage);
		alert.setHeaderText("ERRO");
		alert.show();	
	}
	
	public static void showConfirmation(String mesage) {
		
		javafx.scene.control.Alert alert = new javafx.scene.control.Alert(AlertType.INFORMATION);
		alert.setHeaderText("SUCESSO!");
		alert.setContentText(mesage);
		alert.showAndWait();
		
		if (alert.getResult() != null) {
			alert_true = true;	
		}		
	}
}
