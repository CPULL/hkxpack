package com.dexesttp.afff.gui.data;

import com.dexesttp.afff.model.HkObject;
import com.dexesttp.afff.resources.Utils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DataCellController {
	@FXML private Label title;
	@FXML private Label content;
	@FXML private Label text;

	public void setContent(HkObject obj) {
		title.setText("" + obj.filePos);
		content.setText(Utils.formatBinary(obj.content));
		text.setText(new String(obj.content));
	}

}
