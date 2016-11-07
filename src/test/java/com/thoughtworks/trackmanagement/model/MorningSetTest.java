package com.thoughtworks.trackmanagement.model;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class MorningSetTest {
	
	/**
	 * Add a valid Talk to a session
	 */
	@Test
	public void addValidTalk()
	{
		MorningSession set = new MorningSession();
		Talk talk = new Talk("A filler", 180);
		Assert.assertEquals(true, set.addTalk(talk));
		Assert.assertEquals(0, set.getWastedTime());
	}
	
	/**
	 * Add a valid Talk to a session
	 */
	@Test
	public void FailIfTalkToLong()
	{
		MorningSession set = new MorningSession();
		Talk talk = new Talk("A filler", 190);
		Assert.assertEquals(false, set.addTalk(talk));
	}
	
	/**
	 * Calculate wasted time
	 */
	@Test
	public void getCorrectWastedTime()
	{
		MorningSession set = new MorningSession();
		Talk talk = new Talk("A filler", 10);
		int time = set.MORNING_END - set.MORNING_START - talk.getTime();
		Assert.assertEquals(time, set.getWasteAfterAdd(talk));
	}
	
	/**
	 * Set the correct start time even when time is set.
	 */
	@Test
	public void setCorrectedStartTime()
	{
		MorningSession set = new MorningSession();
		Talk talk = new Talk("A filler", 10);
		talk.setStartTime(60);
		set.addTalk(talk);
		Assert.assertEquals(0, talk.getStartTime());
	}

	/**
	 * Get the correct list of events
	 */
	@Test
	public void correctListOfEvents()
	{
		MorningSession set = new MorningSession();
		Talk talk = new Talk("A filler", 10);
		talk.setStartTime(60);
		set.addTalk(talk);
		List<Event> events = set.getEvents();
		Assert.assertEquals(2, events.size());
		Assert.assertEquals("Lunch", events.get(1).getTitle());
		Assert.assertEquals(set.MORNING_END, events.get(1).getStartTime());
	}
}
