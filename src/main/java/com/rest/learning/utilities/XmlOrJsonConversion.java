package com.rest.learning.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XmlOrJsonConversion {
	
	public static String generateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
