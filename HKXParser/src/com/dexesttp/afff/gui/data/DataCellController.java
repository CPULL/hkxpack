package com.dexesttp.afff.gui.data;

import com.dexesttp.afff.model.hkobjects.HkObject;
import com.dexesttp.afff.resources.Utils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DataCellController {
	@FXML private Label title;
	@FXML private Label content;
	@FXML private Label text;

	public void setContent(HkObject content2) {
		title.setText("" + content2.filePos);
		content.setText(Utils.formatBinary(content2.content));
		text.setText(new String(content2.content));
	}

}
