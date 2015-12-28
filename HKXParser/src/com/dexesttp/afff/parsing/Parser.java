package com.dexesttp.afff.parsing;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.dexesttp.afff.model.structs.Struct;

public interface Parser {
	public Struct parse(RandomAccessFile in) throws IOException;
}
