package com.thoughtworks.trackmanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Session {

	public final int MORNING_START = 0;
	public final int MORNING_END = 180;
	public final int EVENING_START = 240;
	public final int EVENING_END = 480;
	public final int NETWORKING_START = 420;
	public final int NETWORKING_END = 480;
	
	private String track;
	private List<Talk> morningTalks;
	private List<Talk> eveningTalks;
	private int timeMorning;
	private int timeEvening;
	private int timeNetworking;
	
	public Session() {
		morningTalks = new ArrayList<Talk>();
		eveningTalks = new ArrayList<Talk>();
		timeMorning = MORNING_START;
		timeEvening = EVENING_START;
		timeNetworking = NETWORKING_START;
	}

	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
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
	
	public int getTimeNetworking() {
		return timeNetworking;
	}
	
	public void setTimeNetworking(int timeNetworking) {
		this.timeNetworking = timeNetworking;
	}
	
	public boolean addMorningTalk(Talk talk){
		if(timeMorning + talk.getTime() <= MORNING_END){
			morningTalks.add(talk);
			timeMorning += talk.getTime();
			return true;
		}
		return false;
	}
	
	public boolean addEveningTalk(Talk talk){
		if(timeEvening  + talk.getTime() <= EVENING_END){
			eveningTalks.add(talk);
			timeEvening += talk.getTime();
			if(timeEvening > timeNetworking){
				if(timeEvening > NETWORKING_END)
					return false;
				timeNetworking += timeEvening - timeNetworking;
			}
			return true;
		}
		return false;
	}
}
