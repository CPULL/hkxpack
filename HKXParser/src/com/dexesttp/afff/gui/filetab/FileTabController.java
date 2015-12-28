package com.dexesttp.afff.gui.filetab;

import java.net.URL;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import com.dexesttp.afff.gui.classes.ClassCell;
import com.dexesttp.afff.gui.data.DataCell;
import com.dexesttp.afff.model.HkObject;
import com.dexesttp.afff.model.Struct;
import com.dexesttp.afff.model.structs.File_hk_2014_behavior_struct;
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

	private ObservableList<Entry<String, byte[]>> obsClassList = FXCollections.observableArrayList();
	@FXML private ListView<Entry<String, byte[]>> classList;

	private ObservableList<HkObject> obsData1List = FXCollections.observableArrayList();
	@FXML private ListView<HkObject> data1List;
	private ObservableList<HkObject> obsData2List = FXCollections.observableArrayList();
	@FXML private ListView<HkObject> data2List;
	private ObservableList<HkObject> obsData3List = FXCollections.observableArrayList();
	@FXML private ListView<HkObject> data3List;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		classList.setItems(obsClassList);
		classList.setCellFactory(list -> new ClassCell());
		data1List.setItems(obsData1List);
		data1List.setCellFactory(list -> new DataCell());
		data2List.setItems(obsData2List);
		data2List.setCellFactory(list -> new DataCell());
		data3List.setItems(obsData3List);
		data3List.setCellFactory(list -> new DataCell());
	}

	public void loadFile(String fileName, Struct res) {
		fileTab.setText(fileName);
		fillValues(res);
	}

	private void fillValues(Struct res) {
		// Header
		header_head_content.setText(Utils.formatBinary(res.header));
		if(res instanceof File_hk_2014_behavior_struct) {
			header_class_content.setText(Utils.formatBinary(((File_hk_2014_behavior_struct) res).classname));
			header_types_content.setText(Utils.formatBinary(((File_hk_2014_behavior_struct) res).types));
			header_data_content.setText(Utils.formatBinary(((File_hk_2014_behavior_struct) res).data));
		}
		// Classes
		obsClassList.addAll(res.classes.entrySet());
		// Data
		obsData1List.addAll(res.data1);
		obsData2List.addAll(res.data2);
		obsData3List.addAll(res.data3);
	}
}
