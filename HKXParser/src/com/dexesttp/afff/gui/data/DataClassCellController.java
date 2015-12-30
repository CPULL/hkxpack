package com.dexesttp.afff.gui.data;

import com.dexesttp.afff.model.hkobjects.HkClassObject;
import com.dexesttp.afff.resources.Utils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DataClassCellController {
	@FXML private Label className;
	@FXML private Label uid;
	@FXML private Label content;
	@FXML private Label text;
	
	public void setContent(HkClassObject obj) {
		className.setText(obj.className);
		uid.setText(obj.uid + " / " + obj.filePos);
		content.setText(new String(obj.content));
		text.setText(Utils.formatBinary(obj.content));
	}
}
