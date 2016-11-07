package com.thoughtworks.trackmanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Session {

	TalkSet morningSession;
	TalkSet eveningSession;
	
	public Session() {
		morningSession = new MorningSession();
		eveningSession = new AfternoonSession();
	}
	
	public TalkSet getMorningSession() {
		return morningSession;
	}

	public TalkSet getEveningSession() {
		return eveningSession;
	}

	public int getMinimumWasteAfterAdd(Talk talk){
		int m = morningSession.getWasteAfterAdd(talk);
		int e = eveningSession.getWasteAfterAdd(talk);
		if(m >= 0 && e >= 0)
			return Math.min(m, e);
		else if(m < 0 && e < 0)
			return -1;
		else if(m < 0)
			return e;
		else
			return m;
	}
	
	public boolean addTalk(Talk talk){
		int m = morningSession.getWasteAfterAdd(talk);
		int e = eveningSession.getWasteAfterAdd(talk);
		if(e > -1 && m > -1){
			if(m <= e)	
				return morningSession.addTalk(talk);
			else
				return eveningSession.addTalk(talk);
		}else if(e > -1 && m < 0)
			return eveningSession.addTalk(talk);
		else if(m > -1 && e < 0)
			return morningSession.addTalk(talk);
		else
			return false;
	}
	
	public boolean removeTalk(Talk talk) {
		if(!morningSession.removeTalk(talk))
			return eveningSession.removeTalk(talk);
		return true;
	}

	public int getWastedTime(){
		return morningSession.getWastedTime() + eveningSession.getWastedTime();
	}
	
	public List<Event> getDaysEvents(){
		List<Event> events = new ArrayList<Event>();
		events.addAll(morningSession.getEvents());
		events.addAll(eveningSession.getEvents());
		return events;
	}

}
