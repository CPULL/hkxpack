package com.dexesttp.analysis.gui;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NBundle {
	private static final String baseName = "l10n/string";
	private static ResourceBundle instance;
	
	public static ResourceBundle getInstance() {
		initializeInstanceIfNeeded();
		return instance;
	}

	private static void initializeInstanceIfNeeded() {
		if (instance == null)
			instance = ResourceBundle.getBundle(baseName, new Locale("en_US"));
	}

}
