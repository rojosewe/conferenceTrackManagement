package com.thoughtworks.trackmanagement.planner;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.trackmanagement.model.Session;
import com.thoughtworks.trackmanagement.model.Talk;

public class SessionTest {

	/**
	 * Add a valid Talk to a session
	 */
	@Test
	public void addValidTalk()
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 180);
		Assert.assertEquals(true, session.addTalk(talk));
	}
	
	/**
	 * Add a Talk to a full morning session
	 */
	@Test
	public void failAfterAddingFullSession()
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 180);
		Assert.assertEquals(true, session.addMorningTalk(talk));
		Talk talk2 = new Talk("A shorter one", 1);
		Assert.assertEquals(false, session.addMorningTalk(talk2));
	}
	
	/**
	 * Add a Talk to an afternoon session if morning full
	 */
	@Test
	public void addToAfterNoonWhenMorningFull()
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 180);
		Assert.assertTrue(session.addMorningTalk(talk));
		Talk talk2 = new Talk("A shorter one", 1);
		Assert.assertTrue(session.addTalk(talk2));
		Assert.assertTrue(session.getEveningTalks().contains(talk2));
	}
	
	/**
	 * Add a Talk to a full morning session
	 */
	@Test
	public void OKAfterMovingNetworkEvent()
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
	public void FailAfterMovingNetworkEvent()
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 240);
		Assert.assertEquals(true, session.addEveningTalk(talk));
		Talk talk2 = new Talk("A shorter one", 1);
		Assert.assertEquals(false, session.addEveningTalk(talk2));
	}
	
}
