package com.thoughtworks.trackmanagement.utils;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.trackmanagement.exception.InvalidTalkSpecsException;
import com.thoughtworks.trackmanagement.model.Talk;

public class TalkParserTest {
	
	/**
	 * Parse a single talk with valid time
	 * @throws InvalidTalkSpecsException 
	 */
	@Test
	public void testExampleInput() throws InvalidTalkSpecsException {
		String[] talks = new String[]{
				"Writing Fast Tests Against Enterprise Rails 60min",
				"Overdoing it in Python 45min",
				"Lua for the Masses 30min",
				"Ruby Errors from Mismatched Gem Versions 45min",
				"Common Ruby Errors 45min",
				"Rails for Python Developers lightning",
				"Communicating Over Distance 60min",
				"Accounting-Driven Development 45min",
				"Woah 30min",
				"Sit Down and Write 30min",
				"Pair Programming vs Noise 45min",
				"Rails Magic 60min",
				"Ruby on Rails: Why We Should Move On 60min",
				"Clojure Ate Scala (on my project) 45min",
				"Programming in the Boondocks of Seattle 30min",
				"Ruby vs. Clojure for Back-End Development 30min",
				"Ruby on Rails Legacy App Maintenance 60min",
				"A World Without HackerNews 30min",
				"User Interface CSS in Rails Apps 30min"
		};
		List<Talk> list = TalkParser.parseList(Arrays.asList(talks));
		Talk[] trueValues = new Talk[]{
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
		};
		for (int i = 0; i < trueValues.length; i++){
			Talk ground = trueValues[i];
			Talk testing = list.get(i);
			Assert.assertEquals(ground.getTitle(), testing.getTitle());
			Assert.assertEquals(ground.getTime(), testing.getTime());
		}
	}
	
	/**
	 * Parse a single talk with valid time
	 * @throws InvalidTalkSpecsException 
	 */
	@Test
	public void testForValidMinuteSpecification() throws InvalidTalkSpecsException {
		Talk validTalk = TalkParser.parse("Writing Fast Tests Against Enterprise Rails 60min");
		Assert.assertEquals("Writing Fast Tests Against Enterprise Rails", validTalk.getTitle());
		Assert.assertEquals(60, validTalk.getTime());
	}

	/**
	 * Parse a single talk with lightning time
	 * @throws InvalidTalkSpecsException 
	 */
	@Test
	public void testForLightningSpecification() throws InvalidTalkSpecsException {
		Talk validTalk = TalkParser.parse("Rails for Python Developers lightning");
		Assert.assertEquals("Rails for Python Developers", validTalk.getTitle());
		Assert.assertEquals(5, validTalk.getTime());
	}
	
	/**
	 * Parse a single talk with lightning time
	 * @throws InvalidTalkSpecsException 
	 */
	@Test
	public void testForWhiteSpaceCleaning() throws InvalidTalkSpecsException {
		Talk testTalk = TalkParser.parse("     Rails for Python Developers     lightning    ");
		Assert.assertEquals("Rails for Python Developers", testTalk.getTitle());
		Assert.assertEquals(5, testTalk.getTime());
	}
	
	/**
	 * Parse a single talk with lightning time
	 * @throws InvalidTalkSpecsException 
	 */
	@Test(expected = InvalidTalkSpecsException.class)
	public void failIfZeroTime() throws InvalidTalkSpecsException {
		TalkParser.parse("Rails for Python Developers 0min");
	}

	/**
	 * Parse a single talk with lightning time
	 * @throws InvalidTalkSpecsException 
	 */
	@Test
	public void testForShortTitle() throws InvalidTalkSpecsException {
		Talk validTalk = TalkParser.parse("a lightning");
		Assert.assertEquals("a", validTalk.getTitle());
		Assert.assertEquals(5, validTalk.getTime());
	}

	/**
	 * Parse a list of descriptions with different valid times
	 * @throws InvalidTalkSpecsException 
	 */
	@Test
	public void testForDifferentTimes() throws InvalidTalkSpecsException
	{
		String[] data = new String[]{
				"Accounting-Driven Development 45min",
				"Woah 30min",
				"Sit Down and Write 30min",
				"Pair Programming vs Noise 45min",
				"Rails Magic 60min",
				"Ruby on Rails: Why We Should Move On 60min",
				"Clojure Ate Scala (on my project) 45min",
				"Programming in the Boondocks of Seattle 30min",
				"Ruby vs. Clojure for Back-End Development 30min",
				"Ruby on Rails Legacy App Maintenance 60min"		
		};
		String[] titles = new String[]{
				"Accounting-Driven Development",
				"Woah",
				"Sit Down and Write",
				"Pair Programming vs Noise",
				"Rails Magic",
				"Ruby on Rails: Why We Should Move On",
				"Clojure Ate Scala (on my project)",
				"Programming in the Boondocks of Seattle",
				"Ruby vs. Clojure for Back-End Development",
				"Ruby on Rails Legacy App Maintenance"		
		};
		int[] times = new int[]{
				45,30,30,45,60,60,45,30,30,60
		};
		List<Talk> talks = TalkParser.parseList(Arrays.asList(data));
		int i = 0;
		for (Talk talk : talks) {
			Assert.assertEquals(titles[i], talk.getTitle());
			Assert.assertEquals(times[i], talk.getTime());
			i++;
		}
	}


	/**
	 * Parse a single talk description but fail if it has number on name
	 * @throws InvalidTalkSpecsException 
	 */
	@Test(expected = InvalidTalkSpecsException.class)
	public void failIfNumberOnName() throws InvalidTalkSpecsException {
		Talk validTalk = TalkParser.parse("Testing for 2 numbers lightning");
		Assert.assertEquals("Testing for 2 numbers lightning", validTalk.getTitle());
		Assert.assertEquals(5, validTalk.getTime());
	}
	
	/**
	 * Parse a single talk description but fail if it has number on name
	 * @throws InvalidTalkSpecsException 
	 */
	@Test(expected = InvalidTalkSpecsException.class)
	public void failIfNumberIsName() throws InvalidTalkSpecsException {
		Talk validTalk = TalkParser.parse("2 45min");
		Assert.assertEquals("a", validTalk.getTitle());
		Assert.assertEquals(5, validTalk.getTime());
	}

	/**
	 * Parse a single talk description but there is no minute
	 * @throws InvalidTalkSpecsException 
	 */
	@Test(expected = InvalidTalkSpecsException.class)
	public void failIfNoMinuteSpecification() throws InvalidTalkSpecsException {
		TalkParser.parse("Handling exceptions on Validations for Java");
	}

	/**
	 * Parse a single talk and fail for no specified unit.
	 * @throws InvalidTalkSpecsException 
	 */
	@Test(expected = InvalidTalkSpecsException.class)
	public void failIfNoTimeSpecification() throws InvalidTalkSpecsException {
		TalkParser.parse("Handling exceptions on Validations for Java 45");
	}
	
	/**
	 * Parse a single talk and fail for negative specified time.
	 * @throws InvalidTalkSpecsException 
	 */
	@Test(expected = InvalidTalkSpecsException.class)
	public void failIfNegativeTimeSpecification() throws InvalidTalkSpecsException {
		TalkParser.parse("Handling exceptions on Validations for Java -1min");
	}

	/**
	 * Throw an Exception if The title is empty only whitespaces.
	 * that can be allocated
	 * @throws InvalidTalkSpecsException 
	 */
	@Test(expected = InvalidTalkSpecsException.class)
	public void failIfWhiteSpaceOrEmptyTitle() throws InvalidTalkSpecsException {
		TalkParser.parse("         45min");
	}
}
