package com.dexesttp.afff.gui;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;

public class Controller implements Initializable {
	
	@FXML TabPane fileTabList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	private void loadFile() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file...");
        final List<File> selectedFile = fileChooser.showOpenMultipleDialog(null);
        if(selectedFile != null) {
        	for(File f : selectedFile) {
        		fileTabList.getTabs().add(new FileTab(f));
        	}
        }
	}
	
	@FXML
	private void closeFile() {
	}
	
	@FXML
	private void quitApplication() {
		System.exit(0);
	}
}
