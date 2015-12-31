package com.dexesttp.analysis.parsing;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.dexesttp.analysis.model.structs.Struct;

public interface Parser {
	public Struct parse(RandomAccessFile in) throws IOException;
}
