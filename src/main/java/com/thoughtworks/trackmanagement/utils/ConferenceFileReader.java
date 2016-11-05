package com.thoughtworks.trackmanagement.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ConferenceFileReader {

	public List<String> readFile(String path) throws IOException{
		return Files.readAllLines(Paths.get(path));
	}
}
