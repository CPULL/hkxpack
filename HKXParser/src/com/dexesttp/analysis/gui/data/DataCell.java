package com.dexesttp.analysis.gui.data;


import java.io.IOException;
import java.net.URL;

import com.dexesttp.analysis.gui.I18NBundle;
import com.dexesttp.analysis.model.hkobjects.HkObject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

public class DataCell extends ListCell<HkObject>{
	DataCellController controller;
	Node defaultGraphic;
	
	public DataCell() {
		URL location = getClass().getResource("/xml/data_cell.fxml");
		FXMLLoader loader =  new FXMLLoader(location, I18NBundle.getInstance());
		loader.setRoot(this);
		try {
			loader.load();
		} catch(IOException e) {
			e.printStackTrace();
		}
		controller= (DataCellController) loader.getController();
		defaultGraphic = this.getGraphic();
	}
	
	@Override
	protected void updateItem(HkObject content, boolean empty) {
		super.updateItem(content, empty);
		if(empty) {
			this.setGraphic(null);
		} else {
			this.setGraphic(defaultGraphic);
			controller.setContent(content);
		}
	}
}
