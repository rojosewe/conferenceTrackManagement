package com.thoughtworks.trackmanagement.utils;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.trackmanagement.exception.InvalidTalkSpecsException;
import com.thoughtworks.trackmanagement.model.Talk;

public class TalkParser {

	private static final int LIGHTNING_DURATION = 5;

	public static Talk parse(String data) throws InvalidTalkSpecsException {
		Talk talk = new Talk();
		data = data.trim();
		int split = data.lastIndexOf(' ');
		validateLength(data, split);
		String lastValue = data.substring(split + 1);
			if ("lightning".equalsIgnoreCase(lastValue))
				talk.setTime(LIGHTNING_DURATION);
			else {
				validateUnits(lastValue);
				lastValue = lastValue.replace("min", "");
				validateTime(talk, lastValue);
				validatePositiveTime(talk);
			}
		talk.setTitle(data.substring(0, split).trim());
		validateTitle(talk);
		return talk;
	}

	private static void validateTitle(Talk talk) throws InvalidTalkSpecsException {
		if (talk.getTitle().matches(".*[0-9]+.*"))
			throw new InvalidTalkSpecsException("The title must not have numbers in it");
	}

	private static void validateTime(Talk talk, String lastValue) throws InvalidTalkSpecsException {
		try {
			talk.setTime(Integer.valueOf(lastValue));
		} catch (NumberFormatException e) {
			throw new InvalidTalkSpecsException("The duration of a talk must "
					+ "either be 'lightning' or a value in minutes with the format" 
					+ "'45min'.");
		}
	}

	private static void validatePositiveTime(Talk talk) throws InvalidTalkSpecsException {
		if (talk.getTime() < 1)
			throw new InvalidTalkSpecsException("The time must be a positive number of minutes.");
	}

	private static void validateUnits(String lastValue) throws InvalidTalkSpecsException {
		if(!lastValue.contains("min"))
			throw new InvalidTalkSpecsException("The time unit must be specified. Only accepted min.");
	}

	private static void validateLength(String data, int split) throws InvalidTalkSpecsException {
		if (split < 1 || split >= data.length() - 1) {
			throw new InvalidTalkSpecsException("The format or the talks' name " 
					+ "must have a title and a duration.");
		}
	}

	public static List<Talk> parseList(Iterable<String> data) throws InvalidTalkSpecsException {
		List<Talk> talks = new ArrayList<Talk>();
		for (String line : data) {
			talks.add(parse(line));
		}
		return talks;
	}
}
