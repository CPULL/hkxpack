package com.dexesttp.afff.view;

import java.io.IOException;
import java.util.Map.Entry;

import com.dexesttp.afff.Main;
import com.dexesttp.afff.model.Struct;
import com.dexesttp.afff.resources.OptionContainer;
import com.dexesttp.afff.resources.UnhandledFileTypeException;
import com.dexesttp.afff.resources.Utils;

public class Command {
	public static void main(String[] args) {
		OptionContainer optionContainer = parseOptions(args);
		Main main = new Main(optionContainer);
		Struct struct;
		try {
			struct = main.start();

			// Temporary file data output
			System.out.println("Header : ");
			System.out.println(Utils.formatBinary(struct.header));
			for(Entry<String, byte[]> entry : struct.classes.entrySet())
				System.out.println(entry.getKey() + " : " + Utils.formatBinary(entry.getValue()));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnhandledFileTypeException e) {
			e.printStackTrace();
		}
	}

	private static OptionContainer parseOptions(String[] args) {
		OptionContainer options = new OptionContainer();
		options.add("file", "D:\\Documents\\SANDBOX\\FO4\\MeleeBehavior.hkx");
		return options;
	}
}
