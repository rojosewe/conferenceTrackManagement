package com.thoughtworks.trackmanagement.planner;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.trackmanagement.model.Session;
import com.thoughtworks.trackmanagement.model.Talk;

public class SessionTest {

	/**
	 * Add a valid Talk to a session
	 */
	@Test
	public void addValidTalk() throws IOException
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 180);
		Assert.assertEquals(true, session.addTalk(talk));
	}
	
	/**
	 * Add a Talk to a full morning session
	 */
	@Test
	public void failAfterAddingFullSession() throws IOException
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 180);
		Assert.assertEquals(true, session.addMorningTalk(talk));
		Talk talk2 = new Talk("A shorter one", 1);
		Assert.assertEquals(false, session.addMorningTalk(talk2));
	}
	
	/**
	 * Add a Talk to a full morning session
	 */
	@Test
	public void OKAfterMovingNetworkEvent() throws IOException
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 180);
		Assert.assertEquals(true, session.addEveningTalk(talk));
		Talk talk2 = new Talk("A shorter one", 1);
		Assert.assertEquals(true, session.addEveningTalk(talk2));
	}
	
	/**
	 * Add a Talk to a full morning session
	 */
	@Test
	public void FailAfterMovingNetworkEvent() throws IOException
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 240);
		Assert.assertEquals(true, session.addEveningTalk(talk));
		Talk talk2 = new Talk("A shorter one", 1);
		Assert.assertEquals(false, session.addEveningTalk(talk2));
	}
	
}
