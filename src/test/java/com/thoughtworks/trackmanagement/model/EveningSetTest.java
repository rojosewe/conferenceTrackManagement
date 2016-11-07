package com.thoughtworks.trackmanagement.model;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EveningSetTest {
	
	/**
	 * Add a valid Talk to a session
	 */
	@Test
	public void addValidTalk()
	{
		AfternoonSession set = new AfternoonSession();
		Talk talk = new Talk("A filler", 190);
		Assert.assertEquals(true, set.addTalk(talk));
		Assert.assertEquals(0, set.getWastedTime());
		Assert.assertEquals(set.NETWORKING_START + 10, set.getPostEvent().getStartTime());
	}
	
	/**
	 * Add a valid Talk to a session
	 */
	@Test
	public void FailIfTalkTooLong()
	{
		AfternoonSession set = new AfternoonSession();
		Talk talk = new Talk("A filler", 180);
		Talk talk2 = new Talk("A ssecond filler", 61);
		Assert.assertEquals(true, set.addTalk(talk));
		Assert.assertEquals(false, set.addTalk(talk2));
	}
	
	/**
	 * Calculate wasted time
	 */
	@Test
	public void getCorrectWastedTime()
	{
		AfternoonSession set = new AfternoonSession();
		Talk talk = new Talk("A filler", 10);
		int time = set.NETWORKING_START - set.EVENING_START - talk.getTime();
		Assert.assertEquals(time, set.getWasteAfterAdd(talk));
	}
	
	/**
	 * Calculate wasted time
	 */
	@Test
	public void getCorrectWastedTimeAfterMovingNetworking()
	{
		AfternoonSession set = new AfternoonSession();
		Talk talk = new Talk("A filler", 180);
		Talk talk2 = new Talk("A ssecond filler", 50);
		set.addTalk(talk);
		Assert.assertEquals(0, set.getWasteAfterAdd(talk2));
	}
	
	/**
	 * Set the correct start time even when time is set.
	 */
	@Test
	public void setCorrectedStartTime()
	{
		AfternoonSession set = new AfternoonSession();
		Talk talk = new Talk("A filler", 10);
		talk.setStartTime(60);
		set.addTalk(talk);
		Assert.assertEquals(set.EVENING_START, talk.getStartTime());
	}

	/**
	 * Get the correct list of events
	 */
	@Test
	public void correctListOfEvents()
	{
		AfternoonSession set = new AfternoonSession();
		Talk talk = new Talk("A filler", 10);
		set.addTalk(talk);
		List<Event> events = set.getEvents();
		Assert.assertEquals(2, events.size());
		Assert.assertEquals("Networking Event", events.get(1).getTitle());
		Assert.assertTrue(set.getPostEvent().getStartTime() >= set.NETWORKING_START);
	}
}
