package com.thoughtworks.trackmanagement.serialization;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.thoughtworks.trackmanagement.model.Session;
import com.thoughtworks.trackmanagement.model.Talk;

public class ConferenceSerializer {
	
	private static final Integer SESSION_START = 9;
	private static final Integer LUNCH_START = 12;
	private static final Integer EVENING_START = 13;
	private SimpleDateFormat sdf = new SimpleDateFormat("hh:mma");

	private Integer hour = SESSION_START;
	private Integer minute = 0;

	public String serialize(List<Session> sessions) {
		StringBuilder sb = new StringBuilder();
		int track = 0;
		for (Session session : sessions) {
			track++;
			startTheDayAgain();
			sb.append("Track ").append(track).append(":").append(System.lineSeparator());
			for(Talk talk: session.getMorningTalks()){
				serializeTalk(sb, talk);
				minute += talk.getTime();
			}
			hour = LUNCH_START;
			minute = 0;
			sb.append(serializeTime()).append(" Lunch").append(System.lineSeparator());
			hour = EVENING_START;
			minute = 0;
			for(Talk talk: session.getEveningTalks()){
				serializeTalk(sb, talk);
				minute += talk.getTime();
			}
			startTheDayAgain();
			minute = session.getTimeNetworking(); 
			sb.append(serializeTime()).append(" Networking Event").append(System.lineSeparator());
		}
		return sb.toString();
	}

	private void startTheDayAgain() {
		hour = SESSION_START;
		minute = 0;
	}

	private void serializeTalk(StringBuilder sb, Talk talk) {
		String time = serializeTime();
		sb.append(time).append(" ").append(talk.getTitle())
		.append(" ").append(talk.getTime()).append("min").append(System.lineSeparator());
	}

	private String serializeTime() {
		hour += minute / 60;
		minute = minute % 60;
		Calendar cal = Calendar.getInstance();
		cal.set(0, 0, 0, hour, minute, 0);
		String time = sdf.format(cal.getTime());
		return time;
	}
}
