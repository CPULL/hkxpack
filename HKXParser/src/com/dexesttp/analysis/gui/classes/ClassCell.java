package com.dexesttp.analysis.gui.classes;

import java.io.IOException;
import java.net.URL;
import java.util.Map.Entry;

import com.dexesttp.analysis.gui.I18NBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

public class ClassCell extends ListCell<Entry<String, byte[]>> {
	private Node defaultGraphic;
	private ClassCellController controller;

	public ClassCell() {
		URL location = getClass().getResource("/xml/class_cell.fxml");
		FXMLLoader loader =  new FXMLLoader(location, I18NBundle.getInstance());
		loader.setRoot(this);
		try {
			loader.load();
		} catch(IOException e) {
			e.printStackTrace();
		}
		controller = (ClassCellController) loader.getController();
		defaultGraphic = this.getGraphic();
	}
	
	@Override
	protected void updateItem(Entry<String, byte[]> content, boolean empty) {
		super.updateItem(content, empty);
		if(empty) {
			this.setGraphic(null);
		} else {
			this.setGraphic(defaultGraphic);
			controller.setContent(content);
		}
	}
}
