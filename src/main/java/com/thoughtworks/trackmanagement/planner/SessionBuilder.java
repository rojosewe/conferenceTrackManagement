package com.thoughtworks.trackmanagement.planner;

import com.thoughtworks.trackmanagement.model.Session;
import com.thoughtworks.trackmanagement.model.Talk;

public class SessionBuilder {
	
	public boolean addTalkToSessionMorning(Session session, Talk talk){
		return session.addMorningTalk(talk);
	}
	
	public boolean addTalkToSessionEvening(Session session, Talk talk){
		return session.addEveningTalk(talk);
	}
	
	public boolean addTalkToSession(Session session, Talk talk){
		if(!addTalkToSessionMorning(session, talk))
			return addTalkToSessionEvening(session, talk);
		return true;
	}
}
