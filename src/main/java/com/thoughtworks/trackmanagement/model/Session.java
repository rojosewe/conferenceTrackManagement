package com.thoughtworks.trackmanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Session {

	public final int MORNING_START = 0;
	public final int MORNING_END = 180;
	public final int EVENING_START = 240;
	public final int NETWORKING_START = 420;
	public final int NETWORKING_END = 480;
	
	private List<Talk> morningTalks;
	private List<Talk> eveningTalks;
	private MiscEvent lunch;
	private MiscEvent networking;
	private int timeMorning;
	private int timeEvening;
	
	public Session() {
		morningTalks = new ArrayList<Talk>();
		eveningTalks = new ArrayList<Talk>();
		timeMorning = MORNING_START;
		timeEvening = EVENING_START;
		lunch = new MiscEvent("Lunch", 60, MORNING_END);
		networking = new MiscEvent("Networking Event", 60, NETWORKING_START);
	}
	
	public List<Talk> getMorningTalks() {
		return morningTalks;
	}
	
	public List<Talk> getEveningTalks() {
		return eveningTalks;
	}
	
	public int getTimeMorning() {
		return timeMorning;
	}
	
	public int getTimeEvening() {
		return timeEvening;
	}
	
	public boolean addTalk(Talk talk){
		if(!addMorningTalk(talk))
			return addEveningTalk(talk);
		return true;
	}
	
	public boolean addMorningTalk(Talk talk){
		if(timeMorning + talk.getTime() <= MORNING_END){
			talk.setStartTime(timeMorning);
			morningTalks.add(talk);
			timeMorning += talk.getTime();
			return true;
		}
		return false;
	}
	
	public boolean addEveningTalk(Talk talk){
		if(timeEvening  + talk.getTime() <= NETWORKING_END){
			talk.setStartTime(timeEvening);
			eveningTalks.add(talk);
			timeEvening += talk.getTime();
			if(timeEvening > networking.getStartTime()){
				networking.setStartTime(timeEvening);
			}
			return true;
		}
		return false;
	}
	
	public boolean removeTalk(Talk talk) {
		if(morningTalks.contains(talk)){
			morningTalks.remove(talk);
			timeMorning -= talk.getTime();
			return true;
		}else if(eveningTalks.contains(talk)){
			eveningTalks.remove(talk);
			timeEvening -= talk.getTime();
			networking.setStartTime(Math.max(networking.getStartTime() - talk.getTime(), NETWORKING_START));
			timeMorning -= talk.getTime();
			return true;
		}
		return false;
	}

	public int getWastedTime(){
		int wastedMorning = MORNING_END - timeMorning;
		int wastedEvening = networking.getStartTime() - timeEvening;
		return wastedMorning + wastedEvening;
	}
	
	public List<Event> getDaysEvents(){
		List<Event> events = new ArrayList<Event>();
		events.addAll(morningTalks);
		events.add(lunch);
		events.addAll(eveningTalks);
		events.add(networking);
		return events;
	}

}
