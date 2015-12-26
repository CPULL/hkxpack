package com.dexesttp.afff.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

public class FileTab extends Tab {
	private FileTabController controller;
	
	public FileTab(File f) {
		URL location = getClass().getResource("/xml/filetab.fxml");
		FXMLLoader loader =  new FXMLLoader(location, I18NBundle.getInstance());
		loader.setRoot(this);
		try {
			loader.load();
		} catch(IOException e) {
			e.printStackTrace();
		}
		controller = (FileTabController) loader.getController();
		controller.loadFile(f);
	}
	
}
