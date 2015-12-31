package com.dexesttp.analysis.parsing;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.dexesttp.analysis.parsing.parsers.File_hk_2010_behavior_parser;
import com.dexesttp.analysis.parsing.parsers.File_hk_2014_animation_parser;
import com.dexesttp.analysis.parsing.parsers.File_hk_2014_behavior_parser;
import com.dexesttp.analysis.resources.UnhandledFileTypeException;
import com.dexesttp.analysis.resources.Utils;


@SuppressWarnings("rawtypes")
public class ParserFactory {
	@SuppressWarnings("serial")
	private static final Map<String, Class> behaviorParserMap = Collections.unmodifiableMap(new HashMap<String, Class>() {{
		put("hk_2014.1.0-r1", File_hk_2014_behavior_parser.class);
		put("hk_2010.2.0-r1", File_hk_2010_behavior_parser.class);
	}});
	@SuppressWarnings("serial")
	private static final Map<String, Class> animationParserMap = Collections.unmodifiableMap(new HashMap<String, Class>() {{
		put("hk_2014.1.0-r1", File_hk_2014_animation_parser.class);
	}});
	
	
	public static Parser getParser(RandomAccessFile in) throws IOException, UnhandledFileTypeException {
		byte[] type = new byte[4];
		in.seek(60);
		in.read(type);
		byte[] content = new byte[14];
		in.seek(40);
		in.read(content);
		String format = new String(content);
		Map<String, Class> parserMap = null;
		if((Arrays.equals(type, new byte[]{0x15, 0x00, 0x00, 0x00}))
			|| (Arrays.equals(type, new byte[]{-1, -1, -1, -1})))
			parserMap = behaviorParserMap;
		if(Arrays.equals(type, new byte[]{0x15, 0x00, 0x10, 0x00}))
			parserMap = animationParserMap;
		try {
			return (Parser) (parserMap.get(format)).newInstance();
		} catch(Exception e) {
			throw new UnhandledFileTypeException(format + " // " + Utils.formatBinary(type));
		}
	}
}
