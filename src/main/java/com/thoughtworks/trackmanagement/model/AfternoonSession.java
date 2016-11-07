package com.thoughtworks.trackmanagement.model;

import java.util.ArrayList;
import java.util.List;

public class AfternoonSession implements TalkSet{

	public final int EVENING_START = 240;
	public final int NETWORKING_START = 420;
	public final int NETWORKING_END = 480;
	
	private List<Talk> talks;
	private MiscEvent networking;
	private int time;
	
	public AfternoonSession() {
		talks = new ArrayList<Talk>();
		time = EVENING_START;
		networking = new MiscEvent("Networking Event", 60, NETWORKING_START);
	}
	
	public List<Talk> getTalks() {
		return talks;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getWasteAfterAdd(Talk talk) {
		int finished = time + talk.getTime();
		if(networking.getStartTime() >= finished)
			return networking.getStartTime() - finished;
		else{
			if(NETWORKING_END >= finished)
				return 0;
			else
				return -1;
		}
	}

	public boolean addTalk(Talk talk){
		if(time  + talk.getTime() <= NETWORKING_END){
			talk.setStartTime(time);
			talks.add(talk);
			time += talk.getTime();
			if(time > networking.getStartTime()){
				networking.setStartTime(time);
			}
			return true;
		}
		return false;
	}
	
	public boolean removeTalk(Talk talk) {
		if(talks.contains(talk)){
			talks.remove(talk);
			time -= talk.getTime();
			networking.setStartTime(Math.max(networking.getStartTime() - talk.getTime(), NETWORKING_START));
			return true;
		}
		return false;
	}

	public int getWastedTime(){
		return networking.getStartTime() - time;
	}

	public MiscEvent getPostEvent() {
		return networking;
	}
	
	public List<Event> getEvents() {
		ArrayList<Event> list = new ArrayList<Event>();
		list.addAll(getTalks());
		list.add(networking);
		return list;
	}
}
