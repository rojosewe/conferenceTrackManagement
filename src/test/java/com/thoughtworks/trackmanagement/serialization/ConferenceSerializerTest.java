package com.thoughtworks.trackmanagement.serialization;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.trackmanagement.model.Session;
import com.thoughtworks.trackmanagement.model.Talk;

public class ConferenceSerializerTest {

	/**
	 * Serialize One 1-hour conference.
	 * @throws IOException 
	 */
	@Test
	public void serializeShortConference() throws IOException
	{
		Session session = new Session();
		session.addTalk(new Talk("A testing talk for Ruby", 60));
		Session[] sessions = new Session[]{
			session
		};
		ConferenceSerializer serializer = new ConferenceSerializer();
		String serialized = serializer.serialize(Arrays.asList(sessions));
		System.out.println(serialized);
		Assert.assertTrue(serialized.contains("09:00AM A testing talk for Ruby 60min"));
	}
	
	/**
	 * Check lunch is at twelve
	 * @throws IOException 
	 */
	@Test
	public void checkForLunch() throws IOException
	{
		Session session = new Session();
		session.addTalk(new Talk("A testing talk for Ruby", 60));
		Session[] sessions = new Session[]{
			session
		};
		ConferenceSerializer serializer = new ConferenceSerializer();
		String serialized = serializer.serialize(Arrays.asList(sessions));
		Assert.assertTrue(serialized.contains("12:00PM Lunch"));
	}
	
	/**
	 * Check networking is there
	 * @throws IOException 
	 */
	@Test
	public void checkForNetworkingEvent() throws IOException
	{
		Session session = new Session();
		session.addTalk(new Talk("A testing talk for Ruby", 60));
		Session[] sessions = new Session[]{
			session
		};
		ConferenceSerializer serializer = new ConferenceSerializer();
		String serialized = serializer.serialize(Arrays.asList(sessions));
		Assert.assertTrue(serialized.contains("04:00PM Networking Event"));
	}
	
	/**
	 * Check Line breaking is working properly
	 * @throws IOException 
	 */
	@Test
	public void checkForLineBreaking() throws IOException
	{
		Session session = new Session();
		session.addTalk(new Talk("A testing talk for Ruby", 60));
		session.addTalk(new Talk("A testing talk for Ruby 2", 60));
		Session[] sessions = new Session[]{
			session
		};
		ConferenceSerializer serializer = new ConferenceSerializer();
		String serialized = serializer.serialize(Arrays.asList(sessions));
		Assert.assertEquals(serialized.split(System.lineSeparator()).length, 5);
	}
	
	/**
	 * Check Line breaking is working properly
	 * @throws IOException 
	 */
	@Test
	public void ifEmptyConference() throws IOException
	{
		Session session = new Session();
		Session[] sessions = new Session[]{
			session
		};
		ConferenceSerializer serializer = new ConferenceSerializer();
		String serialized = serializer.serialize(Arrays.asList(sessions));
		StringBuilder sb = new StringBuilder("Track 1:").append(System.lineSeparator())
				.append("12:00PM Lunch").append(System.lineSeparator())
				.append("04:00PM Networking Event").append(System.lineSeparator());
		Assert.assertEquals(serialized, sb.toString());
	}
}
