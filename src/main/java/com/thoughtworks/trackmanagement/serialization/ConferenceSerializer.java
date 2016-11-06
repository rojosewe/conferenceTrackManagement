package com.thoughtworks.trackmanagement.serialization;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.thoughtworks.trackmanagement.model.Event;
import com.thoughtworks.trackmanagement.model.Session;
import com.thoughtworks.trackmanagement.model.Talk;

public class ConferenceSerializer {
	
	private static final Integer SESSION_START = 9 * 60;
	private SimpleDateFormat sdf = new SimpleDateFormat("hh:mma");

	public String serialize(List<Session> sessions) {
		StringBuilder sb = new StringBuilder();
		int track = 0;
		for (Session session : sessions) {
			track++;
			sb.append("Track ").append(track).append(":").append(System.lineSeparator());
			List<Event> daysEvents = session.getDaysEvents();
			for (Event event : daysEvents) {
				serializeEvent(sb, event);
			}
		}
		return sb.toString();
	}

	private void serializeEvent(StringBuilder sb, Event event) {
		String time = serializeTime(event);
		sb.append(time).append(" ").append(event.toString()).append(System.lineSeparator());
	}

	private String serializeTime(Event event) {
		int time = SESSION_START + event.getStartTime();
		int hour = time / 60;
		int minute = time % 60;
		Calendar cal = Calendar.getInstance();
		cal.set(0, 0, 0, hour, minute, 0);
		return sdf.format(cal.getTime());
	}
}
