package com.dexesttp.analysis.gui.filetab;

import java.net.URL;
import java.util.Map.Entry;

import com.dexesttp.analysis.gui.classes.ClassCell;
import com.dexesttp.analysis.gui.data.DataCell;
import com.dexesttp.analysis.gui.data.DataClassCell;
import com.dexesttp.analysis.model.hkobjects.HkClassObject;
import com.dexesttp.analysis.model.hkobjects.HkObject;
import com.dexesttp.analysis.model.structs.Struct;
import com.dexesttp.analysis.resources.Utils;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

public class FileTabController implements Initializable{
	@FXML private Tab fileTab;
	
	// Headers
	@FXML private Label header_head_content;
	@FXML private Label header_class_content;
	@FXML private Label header_types_content;
	@FXML private Label header_data_content;

	// Class lists
	private ObservableList<Entry<String, byte[]>> obsClassList = FXCollections.observableArrayList();
	@FXML private ListView<Entry<String, byte[]>> classList;

	// Data lists
	private ObservableList<HkObject> obsData1List = FXCollections.observableArrayList();
	@FXML private ListView<HkObject> data1List;
	private ObservableList<HkObject> obsData2List = FXCollections.observableArrayList();
	@FXML private ListView<HkObject> data2List;
	private ObservableList<HkClassObject> obsData3List = FXCollections.observableArrayList();
	@FXML private ListView<HkClassObject> data3List;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		classList.setItems(obsClassList);
		classList.setCellFactory(list -> new ClassCell());
		data1List.setItems(obsData1List);
		data1List.setCellFactory(list -> new DataCell());
		data2List.setItems(obsData2List);
		data2List.setCellFactory(list -> new DataCell());
		data3List.setItems(obsData3List);
		data3List.setCellFactory(list -> new DataClassCell());
	}

	public void loadFile(String fileName, Struct res) {
		fileTab.setText(fileName);
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
		// Data
		obsData1List.addAll(res.data1);
		obsData2List.addAll(res.data2);
		obsData3List.addAll(res.data3);
	}
}
