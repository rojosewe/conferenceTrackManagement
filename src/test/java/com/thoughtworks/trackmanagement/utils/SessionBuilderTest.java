package com.thoughtworks.trackmanagement.utils;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.trackmanagement.model.Session;
import com.thoughtworks.trackmanagement.model.Talk;
import com.thoughtworks.trackmanagement.planner.SessionBuilder;

public class SessionBuilderTest {

	/**
	 * Add a valid Talk to a session
	 */
	@Test
	public void addValidTalk() throws IOException
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 180);
		SessionBuilder builder = new SessionBuilder();
		Assert.assertEquals(true, builder.addTalkToSession(session, talk));
	}
	
	/**
	 * Add a Talk to a full morning session
	 */
	@Test
	public void failAfterAddingFullSession() throws IOException
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 180);
		SessionBuilder builder = new SessionBuilder();
		Assert.assertEquals(true, builder.addTalkToSessionMorning(session, talk));
		Talk talk2 = new Talk("A shorter one", 1);
		Assert.assertEquals(false, builder.addTalkToSessionMorning(session, talk2));
	}
	
	/**
	 * Add a Talk to a full morning session
	 */
	@Test
	public void OKAfterMovingNetworkEvent() throws IOException
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 180);
		SessionBuilder builder = new SessionBuilder();
		Assert.assertEquals(true, builder.addTalkToSessionEvening(session, talk));
		Talk talk2 = new Talk("A shorter one", 1);
		Assert.assertEquals(true, builder.addTalkToSessionEvening(session, talk2));
	}
	
	/**
	 * Add a Talk to a full morning session
	 */
	@Test
	public void FailAfterMovingNetworkEvent() throws IOException
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 240);
		SessionBuilder builder = new SessionBuilder();
		Assert.assertEquals(true, builder.addTalkToSessionEvening(session, talk));
		Talk talk2 = new Talk("A shorter one", 1);
		Assert.assertEquals(false, builder.addTalkToSessionEvening(session, talk2));
	}
	
}
