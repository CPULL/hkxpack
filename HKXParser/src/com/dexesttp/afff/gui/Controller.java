package com.dexesttp.afff.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.dexesttp.afff.Main;
import com.dexesttp.afff.gui.filetab.FileTab;
import com.dexesttp.afff.gui.utils.AlertUtils;
import com.dexesttp.afff.model.structs.Struct;
import com.dexesttp.afff.resources.OptionContainer;
import com.dexesttp.afff.resources.UnhandledFileTypeException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;

public class Controller implements Initializable {
	
	@FXML TabPane fileTabList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// NO OP
	}
	
	private Struct getStruct(File f) {
		OptionContainer options = new OptionContainer();
		options.add("file", f.getAbsolutePath());
		Main main = new Main(options);
		Struct res = null;
		try {
			res = main.start();
		} catch (IOException e) {
			AlertUtils.showAlert(e);
		} catch (UnhandledFileTypeException e) {
			AlertUtils.showAlert(e);
		}
		return res;
	}

	@FXML
	private void loadFile() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file...");
        final List<File> selectedFile = fileChooser.showOpenMultipleDialog(null);
        if(selectedFile != null) {
        	for(File f : selectedFile) {
        		Struct res = getStruct(f);
        		if(res != null)
        			fileTabList.getTabs().add(new FileTab(f.getName(), res));
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
