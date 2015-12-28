package com.dexesttp.afff.parsing;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.dexesttp.afff.parsing.parsers.File_hk_2010_behavior_parser;
import com.dexesttp.afff.parsing.parsers.File_hk_2014_behavior_parser;
import com.dexesttp.afff.resources.UnhandledFileTypeException;

public class ParserFactory {
	@SuppressWarnings({ "rawtypes", "serial" })
	private static final Map<String, Class> parserMap = Collections.unmodifiableMap(new HashMap<String, Class>() {{
		put("hk_2014.1.0-r1", File_hk_2014_behavior_parser.class);
		put("hk_2010.2.0-r1", File_hk_2010_behavior_parser.class);
	}});
	
	public static Parser getParser(RandomAccessFile in) throws IOException, UnhandledFileTypeException {
		byte[] content = new byte[14];
		in.seek(40);
		in.read(content);
		String format = new String(content);
		try {
			return (Parser) (parserMap.get(format)).newInstance();
		} catch(Exception e) {
			throw new UnhandledFileTypeException(format);
		}
	}
}
