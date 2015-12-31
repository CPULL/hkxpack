package com.dexesttp.analysis.gui.filetab;

import java.io.IOException;
import java.net.URL;

import com.dexesttp.analysis.gui.I18NBundle;
import com.dexesttp.analysis.model.structs.Struct;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

public class FileTab extends Tab {
	private FileTabController controller;
	
	public FileTab(String fileName, Struct res) {
		URL location = getClass().getResource("/xml/filetab.fxml");
		FXMLLoader loader =  new FXMLLoader(location, I18NBundle.getInstance());
		loader.setRoot(this);
		try {
			loader.load();
		} catch(IOException e) {
			e.printStackTrace();
		}
		controller = (FileTabController) loader.getController();
		controller.loadFile(fileName, res);
	}
	
}
