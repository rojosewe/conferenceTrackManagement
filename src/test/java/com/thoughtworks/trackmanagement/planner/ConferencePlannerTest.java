package com.thoughtworks.trackmanagement.planner;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.trackmanagement.exception.InvalidTalkSpecsException;
import com.thoughtworks.trackmanagement.model.Talk;

public class ConferencePlannerTest {

	/**
	 * Add a valid lot to the Conference
	 */
	@Test
	public void addTalksAndGetSomething(){
		List<Talk> talks = Arrays.asList(new Talk[]{
				new Talk("Accounting-Driven Development", 30),
				new Talk("Woah", 45),
				new Talk("Sit Down and Write", 5),
				new Talk("Pair Programming vs Noise", 10),
				new Talk("Rails Magic", 20),
				new Talk("Ruby on Rails: Why We Should Move On", 6),
				new Talk("Clojure Ate Scala (on my project)", 20),
				new Talk("Programming in the Boondocks of Seattle", 15),
				new Talk("Ruby vs. Clojure for Back-End Development", 8),
				new Talk("Ruby on Rails Legacy App Maintenance", 25)
		});
		ConferencePlanner planner = new ConferencePlanner();
		planner.buildSessions(talks);
		Assert.assertTrue(planner.getWasted() > -1);
		Assert.assertTrue(planner.getSessions().size() > 0);
	}
	
	/**
	 * If empty, return empty.
	 */
	@Test
	public void ProcessEmptyListOfTalks(){
		List<Talk> talks = Arrays.asList(new Talk[]{});
		ConferencePlanner planner = new ConferencePlanner();
		Assert.assertTrue(planner.buildSessions(talks));
		Assert.assertTrue(planner.getWasted() == 0);
		Assert.assertTrue(planner.getSessions().size() == 0);
	}
	
	/**
	 * The largest talk time cannot be larger than the largest possible slot.
	 */
	@Test(expected = InvalidTalkSpecsException.class)
	public void FailIfLargestTalkIsTooBig(){
		List<Talk> talks = Arrays.asList(new Talk[]{
				new Talk("Accounting-Driven Development", 300)
		});
		ConferencePlanner planner = new ConferencePlanner();
		planner.buildSessions(talks);
	}
}
