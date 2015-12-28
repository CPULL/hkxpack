package com.dexesttp.afff.view;

import java.net.URL;

import com.dexesttp.afff.gui.I18NBundle;
import com.dexesttp.afff.gui.utils.AlertUtils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Interface extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		final URL url = getClass().getResource("/xml/base.fxml");
		final URL cssUrl = getClass().getResource("/css/layout.css");
		final FXMLLoader fxmlLoader = new FXMLLoader(url, I18NBundle.getInstance());
		final BorderPane root = (BorderPane) fxmlLoader.load();
		
		final Scene scene = new Scene(root, 1280, 1024);
		AlertUtils.setDialogStage(primaryStage);
		scene.getStylesheets().add(cssUrl.toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("HKXCmd - interface");
		primaryStage.show();
	}
}
