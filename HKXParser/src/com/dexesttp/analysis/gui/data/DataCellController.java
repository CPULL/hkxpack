package com.dexesttp.analysis.gui.data;

import com.dexesttp.analysis.model.hkobjects.HkObject;
import com.dexesttp.analysis.resources.Utils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DataCellController {
	@FXML protected Label title;
	@FXML protected Label content;
	@FXML protected Label text;

	public void setContent(HkObject obj) {
		title.setText(obj.uid + " / " + obj.filePos);
		content.setText(Utils.formatBinary(obj.content));
		text.setText(new String(obj.content));
	}

}
