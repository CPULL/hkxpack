package com.dexesttp.analysis.gui.classes;

import java.util.Map.Entry;

import com.dexesttp.analysis.resources.Utils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClassCellController {
	@FXML private Label title;
	@FXML private Label content;
	
	public void setContent(Entry<String, byte[]> value) {
		title.setText(value.getKey());
		content.setText(Utils.formatBinary(value.getValue()));
	}

}
