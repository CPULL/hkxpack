package com.dexesttp.analysis.gui.data;

import java.io.IOException;
import java.net.URL;

import com.dexesttp.analysis.gui.I18NBundle;
import com.dexesttp.analysis.model.hkobjects.HkClassObject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

public class DataClassCell  extends ListCell<HkClassObject> {
	DataClassCellController controller;
	Node defaultGraphic;
	
	public DataClassCell() {
		URL location = getClass().getResource("/xml/data_class_cell.fxml");
		FXMLLoader loader =  new FXMLLoader(location, I18NBundle.getInstance());
		loader.setRoot(this);
		try {
			loader.load();
		} catch(IOException e) {
			e.printStackTrace();
		}
		controller= (DataClassCellController) loader.getController();
		defaultGraphic = this.getGraphic();
	}
	
	@Override
	protected void updateItem(HkClassObject content, boolean empty) {
		super.updateItem(content, empty);
		if(empty) {
			this.setGraphic(null);
		} else {
			this.setGraphic(defaultGraphic);
			controller.setContent(content);
		}
	}
}
