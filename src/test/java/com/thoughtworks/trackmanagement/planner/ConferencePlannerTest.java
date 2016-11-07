package com.thoughtworks.trackmanagement.planner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.trackmanagement.exception.InvalidTalkSpecsException;
import com.thoughtworks.trackmanagement.model.Session;
import com.thoughtworks.trackmanagement.model.Talk;
import com.thoughtworks.trackmanagement.serialization.ConferenceSerializer;
import com.thoughtworks.trackmanagement.serialization.ConferenceSerializer;

public class ConferencePlannerTest {

	/**
	 * Add a valid lot to the Conference
	 * @throws InvalidTalkSpecsException 
	 */
	@Test
	public void addTalksAndGetSomething() throws InvalidTalkSpecsException{
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
		Assert.assertEquals(allIncluded(planner.getSessions(), talks), 0);
	}
	
	/**
	 * Add a valid lot to the Conference
	 * @throws InvalidTalkSpecsException 
	 */
	@Test
	public void addDefaultTestTalks() throws InvalidTalkSpecsException{
		List<Talk> talks = Arrays.asList(new Talk[]{
				new Talk("Writing Fast Tests Against Enterprise Rails", 60),
				new Talk("Overdoing it in Python", 45),
				new Talk("Lua for the Masses", 30),
				new Talk("Ruby Errors from Mismatched Gem Versions", 45),
				new Talk("Common Ruby Errors", 45),
				new Talk("Rails for Python Developers", 5),
				new Talk("Communicating Over Distance", 60),
				new Talk("Accounting-Driven Development", 45),
				new Talk("Woah", 30),
				new Talk("Sit Down and Write", 30),
				new Talk("Pair Programming vs Noise", 45),
				new Talk("Rails Magic", 60),
				new Talk("Ruby on Rails: Why We Should Move On", 60),
				new Talk("Clojure Ate Scala (on my project)", 45),
				new Talk("Programming in the Boondocks of Seattle", 30),
				new Talk("Ruby vs. Clojure for Back-End Development", 30),
				new Talk("Ruby on Rails Legacy App Maintenance", 60),
				new Talk("A World Without HackerNews", 30),
				new Talk("User Interface CSS in Rails Apps", 30)
		});
		ConferencePlanner planner = new ConferencePlanner();
		planner.buildSessions(talks);
		Assert.assertTrue(planner.getWasted() > -1);
		Assert.assertTrue(planner.getSessions().size() > 0);
		Assert.assertEquals(allIncluded(planner.getSessions(), talks), 0);
		ConferenceSerializer s = new ConferenceSerializer();
		System.out.println(s.serialize(planner.getSessions()));
	}
	
	/**
	 * Add a valid lot to the Conference
	 * @throws InvalidTalkSpecsException 
	 */
	@Test
	public void testForVeryLongConference() throws InvalidTalkSpecsException{
		List<Talk> talks = new ArrayList<Talk>();
		String letters = "ABCDEFGHTIJKLMNOPQRSTUVWXYZ";
		for(Integer i = 0; i < 1000; i++){
			talks.add(new Talk("" + letters.charAt(i % letters.length()), (int)(Math.random()*100 + 1)));
		}
		ConferencePlanner planner = new ConferencePlanner();
		planner.buildSessions(talks);
		System.out.println(planner.getWasted());
		Assert.assertTrue(planner.getWasted() > -1);
		Assert.assertTrue(planner.getSessions().size() > 0);
		Assert.assertEquals(allIncluded(planner.getSessions(), talks), 0);
		ConferenceSerializer s = new ConferenceSerializer();
		System.out.println(s.serialize(planner.getSessions()));
	}
	
	/**
	 * If empty, return empty.
	 * @throws InvalidTalkSpecsException 
	 */
	@Test
	public void ProcessEmptyListOfTalks() throws InvalidTalkSpecsException{
		List<Talk> talks = Arrays.asList(new Talk[]{});
		ConferencePlanner planner = new ConferencePlanner();
		Assert.assertTrue(planner.buildSessions(talks));
		Assert.assertTrue(planner.getWasted() == 0);
		Assert.assertTrue(planner.getSessions().size() == 0);
	}
	
	/**
	 * If empty, return empty.
	 * @throws InvalidTalkSpecsException 
	 */
	@Test
	public void ProcessSeveralBigTalks() throws InvalidTalkSpecsException{
		List<Talk> talks = Arrays.asList(new Talk[]{
				new Talk("Writing Fast Tests Against Enterprise Rails", 180),
				new Talk("Overdoing it in Python", 180),
				new Talk("Lua for the Masses", 190),
				new Talk("Ruby Errors from Mismatched Gem Versions", 180),
				new Talk("Woah", 180),
		});
		ConferencePlanner planner = new ConferencePlanner();
		Assert.assertTrue(planner.buildSessions(talks));
		ConferenceSerializer s = new ConferenceSerializer();
		System.out.println(s.serialize(planner.getSessions()));
		Assert.assertEquals(planner.getSessions().size(), 3);
		Assert.assertEquals(allIncluded(planner.getSessions(), talks), 0);
	}
	
	private int allIncluded(List<Session> sessions, List<Talk> talks){
		int target = talks.size();
		for (Session session: sessions) {
			target -= session.getMorningSession().getTalks().size() +
					session.getEveningSession().getTalks().size();
		}
		return target;
	}
	
	/**
	 * The largest talk time cannot be larger than the largest possible slot.
	 * @throws InvalidTalkSpecsException 
	 */
	@Test(expected = InvalidTalkSpecsException.class)
	public void FailIfLargestTalkIsTooBig() throws InvalidTalkSpecsException{
		List<Talk> talks = Arrays.asList(new Talk[]{
				new Talk("Accounting-Driven Development", 300)
		});
		ConferencePlanner planner = new ConferencePlanner();
		planner.buildSessions(talks);
	}
}
