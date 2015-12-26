package com.dexesttp.afff.parsing;

import java.io.RandomAccessFile;

import com.dexesttp.afff.model.Struct;

public interface Parser {
	public Struct parse(RandomAccessFile in);
}
