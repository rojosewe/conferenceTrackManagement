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
	
	public boolean addTalk(Talk talk){
		if(!addMorningTalk(talk))
			return addEveningTalk(talk);
		return false;
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
		if(timeEvening  + talk.getTime() <= NETWORKING_END){
			eveningTalks.add(talk);
			timeEvening += talk.getTime();
			if(timeEvening > timeNetworking){
				timeNetworking += timeEvening - timeNetworking;
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
			timeNetworking = Math.max(timeNetworking - talk.getTime(), NETWORKING_START);
			timeMorning -= talk.getTime();
			return true;
		}
		return false;
	}

	public int getWastedTime(){
		int wastedMorning = MORNING_END - timeMorning;
		int wastedEvening = NETWORKING_START - timeEvening;
		return wastedMorning + wastedEvening;
	}

}
