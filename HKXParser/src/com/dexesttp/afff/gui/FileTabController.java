package com.dexesttp.afff.gui;

import java.io.File;

import com.dexesttp.afff.Main;
import com.dexesttp.afff.gui.classes.ClassListController;
import com.dexesttp.afff.model.Struct;
import com.dexesttp.afff.resources.OptionContainer;
import com.dexesttp.afff.resources.Utils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

public class FileTabController {
	@FXML private Tab fileTab;
	@FXML private Label header_head_content;
	@FXML private Label header_class_content;
	@FXML private Label header_types_content;
	@FXML private Label header_data_content;
	@FXML private ClassListController classListController;
	@FXML private Label behGraph_content;
	@FXML private Label incFile_content;

	public void loadFile(File f) {
		OptionContainer options = new OptionContainer();
		options.add("file", f.getAbsolutePath());
		Main main = new Main(options);
		Struct res = main.start();
		fileTab.setText(f.getName());
		fillValues(res);
	}

	private void fillValues(Struct res) {
		header_head_content.setText(Utils.formatBinary(res.header));
		header_class_content.setText(Utils.formatBinary(res.classname));
		header_types_content.setText(Utils.formatBinary(res.types));
		header_data_content.setText(Utils.formatBinary(res.data));
		classListController.fill(res.classes.entrySet());
		behGraph_content.setText(Utils.formatBinary(res.behgraph));
		incFile_content.setText(Utils.formatBinary(res.fileHeader));
	}
}
