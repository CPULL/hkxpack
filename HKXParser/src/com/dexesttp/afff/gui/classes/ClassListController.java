package com.dexesttp.afff.gui.classes;

import java.net.URL;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ClassListController implements Initializable {
	@FXML private ListView<Entry<String, byte[]>> classList;
	
	private ObservableList<Entry<String, byte[]>> observableClassList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		classList.setItems(observableClassList);
		classList.setCellFactory(list -> new ClassCell());
	}
	
	public void fill(Collection<Entry<String, byte[]>> contents) {
		observableClassList.addAll(contents);
	}
}
