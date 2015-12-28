package com.dexesttp.afff.gui.utils;

import java.io.IOException;

import com.dexesttp.afff.resources.UnhandledFileTypeException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AlertUtils {
	private static Stage dialogStage;
	public static void setDialogStage(Stage stage) {
		dialogStage = stage;
	}
	
	public static void showAlert(UnhandledFileTypeException e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(dialogStage);
		alert.setTitle("Wrong file format");
		alert.setHeaderText("The format wasn't recognized !");
		alert.setContentText(e.toString() + " : " + e.getMessage());
		e.printStackTrace();
		alert.showAndWait();
	}

	public static void showAlert(IOException e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(dialogStage);
		alert.setTitle("Input error");
		alert.setHeaderText("There was a problem reading the file.");
		alert.setContentText(e.toString() + " : " + e.getMessage());
		e.printStackTrace();
		alert.showAndWait();
	}
}
