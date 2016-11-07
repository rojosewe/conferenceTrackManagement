package com.thoughtworks.trackmanagement.model;

import java.util.ArrayList;
import java.util.List;

public class MorningSession implements TalkSet{

	public final int MORNING_START = 0;
	public final int MORNING_END = 180;

	private List<Talk> talks;
	private MiscEvent lunch;
	private int time;
	
	public MorningSession() {
		talks = new ArrayList<Talk>();
		time = MORNING_START;
		lunch = new MiscEvent("Lunch", 60, MORNING_END);
	}
	
	public List<Talk> getTalks() {
		return talks;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getWasteAfterAdd(Talk talk) {
		return MORNING_END - (time + talk.getTime());
	}

	public boolean addTalk(Talk talk){
		if(time + talk.getTime() <= MORNING_END){
			talk.setStartTime(time);
			talks.add(talk);
			time += talk.getTime();
			return true;
		}
		return false;
	}
	
	public boolean removeTalk(Talk talk) {
		if(talks.contains(talk)){
			talks.remove(talk);
			time -= talk.getTime();
			return true;
		}
		return false;
	}

	public int getWastedTime(){
		return MORNING_END - time;
	}
	
	public MiscEvent getPostEvent(){
		return lunch;
	}

	public List<Event> getEvents() {
		ArrayList<Event> list = new ArrayList<Event>();
		list.addAll(getTalks());
		list.add(lunch);
		return list;
	}
	
}
