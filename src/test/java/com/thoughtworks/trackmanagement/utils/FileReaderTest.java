package com.thoughtworks.trackmanagement.utils;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.trackmanagement.utils.ConferenceFileReader;

public class FileReaderTest {

	/**
	 * Read a valid file with a list of valid talks
	 * @throws IOException 
	 */
	@Test
	public void readValidFile() throws IOException
	{
		ConferenceFileReader reader = new ConferenceFileReader();
		List<String> specs = reader.readFile("src/test/resources/ValidFile.txt");
		Assert.assertEquals(specs.size(), 19);
	}
	
    /**
     * Read a file that's empty and return an error.
     * @throws IOException 
     */
	@Test
    public void readEmptyFile() throws IOException
    {
		ConferenceFileReader reader = new ConferenceFileReader();
		List<String> specs = reader.readFile("src/test/resources/EmptyFile.txt");
		Assert.assertEquals(specs.size(), 0);
    }
	
	
	/**
     * Make sure the exception is thrown and not swallowed by the inner class.
     * This allows the developer to handle the exception externally
	 * @throws IOException 
     */
	@Test(expected = IOException.class)
    public void failAfterFileNotFound() throws IOException
    {
			ConferenceFileReader reader = new ConferenceFileReader();
			reader.readFile("src/test/resources/NonExist.txt");
    }
	
	/**
     * 
     */
	@Test 
    public void testForAccentedCharacters() throws IOException{
			ConferenceFileReader reader = new ConferenceFileReader();
			reader.readFile("src/test/resources/strange.txt");
    }
}
