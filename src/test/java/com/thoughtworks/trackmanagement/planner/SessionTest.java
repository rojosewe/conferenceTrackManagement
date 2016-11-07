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
		Assert.assertEquals(true, session.getMorningSession().addTalk(talk));
		Talk talk2 = new Talk("A shorter one", 1);
		Assert.assertEquals(false, session.getMorningSession().addTalk(talk2));
	}
	
	/**
	 * Add a Talk to an afternoon session if morning full
	 */
	@Test
	public void addToAfterNoonWhenMorningFull()
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 180);
		Assert.assertTrue(session.addTalk(talk));
		Talk talk2 = new Talk("A shorter one", 1);
		Assert.assertTrue(session.addTalk(talk2));
		Assert.assertTrue(session.getEveningSession().getTalks().contains(talk2));
	}
	
	/**
	 * Add a Talk to a full morning session
	 */
	@Test
	public void OKAfterMovingNetworkEvent()
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 180);
		Assert.assertEquals(true, session.getEveningSession().addTalk(talk));
		Talk talk2 = new Talk("A shorter one", 1);
		Assert.assertEquals(true, session.getEveningSession().addTalk(talk2));
	}
	
	/**
	 * Add a Talk to a full morning session
	 */
	@Test
	public void FailAfterMovingNetworkEvent()
	{
		Session session = new Session();
		Talk talk = new Talk("A filler", 240);
		Assert.assertEquals(true, session.getEveningSession().addTalk(talk));
		Talk talk2 = new Talk("A shorter one", 1);
		Assert.assertEquals(false, session.getEveningSession().addTalk(talk2));
	}
	
}
