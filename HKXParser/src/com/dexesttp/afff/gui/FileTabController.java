package com.dexesttp.afff.gui;

import java.io.File;
import java.net.URL;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import com.dexesttp.afff.Main;
import com.dexesttp.afff.gui.classes.ClassCell;
import com.dexesttp.afff.gui.data.DataCell;
import com.dexesttp.afff.model.HkObject;
import com.dexesttp.afff.model.Struct;
import com.dexesttp.afff.resources.OptionContainer;
import com.dexesttp.afff.resources.Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

public class FileTabController implements Initializable{
	@FXML private Tab fileTab;
	@FXML private Label header_head_content;
	@FXML private Label header_class_content;
	@FXML private Label header_types_content;
	@FXML private Label header_data_content;
	@FXML private Label behGraph_content;
	@FXML private Label incFile_content;

	private ObservableList<Entry<String, byte[]>> obsClassList = FXCollections.observableArrayList();
	@FXML private ListView<Entry<String, byte[]>> classList;

	private ObservableList<HkObject> obsDataList = FXCollections.observableArrayList();
	@FXML private ListView<HkObject> dataList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		classList.setItems(obsClassList);
		classList.setCellFactory(list -> new ClassCell());
		dataList.setItems(obsDataList);
		dataList.setCellFactory(list -> new DataCell());
	}

	public void loadFile(File f) {
		OptionContainer options = new OptionContainer();
		options.add("file", f.getAbsolutePath());
		Main main = new Main(options);
		Struct res = main.start();
		fileTab.setText(f.getName());
		fillValues(res);
	}

	private void fillValues(Struct res) {
		// Header
		header_head_content.setText(Utils.formatBinary(res.header));
		header_class_content.setText(Utils.formatBinary(res.classname));
		header_types_content.setText(Utils.formatBinary(res.types));
		header_data_content.setText(Utils.formatBinary(res.data));
		// Classes
		obsClassList.addAll(res.classes.entrySet());
		// Temporary content
		behGraph_content.setText(Utils.formatBinary(res.behgraph));
		incFile_content.setText(Utils.formatBinary(res.fileHeader));
		// Data
		obsDataList.addAll(res.data1);
	}
}
