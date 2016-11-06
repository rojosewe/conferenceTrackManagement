package com.thoughtworks.trackmanagement.planner;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.trackmanagement.model.Session;
import com.thoughtworks.trackmanagement.model.Talk;

/**
 * The conference planner can be seen as a bin packing problem. An algorithm 
 * must be chosen from the many possible solutions. We can focus on maximizing speed, 
 * simplicity and / or best possible result.
 * 
 * There are two possible ways to evaluate the results. By minimizing the sessions
 * and minimizing the amount of wasted time by session. We will approach by minimizing
 * the number of sessions and in case of a tie, minimizing wasted time.
 * 
 * @author Rodrigo J Weffer
 *
 */
public class ConferencePlanner {

	ArrayList<Session> sessions = new ArrayList<Session>();
	ArrayList<Boolean[]> sessionTalkMatch = new ArrayList<Boolean[]>();
	
	
	public boolean buildSessions(List<Talk> talks) {
		Collections.sort(talks);
		if(!new Session().addTalk(talks.get(0)))
			System.out.println("No solution.");
		sessions.add(new Session());
		sessionTalkMatch.add(new Boolean[talks.size()]);
		return schedule(talks, 0);
		
	}
	
	private boolean schedule(List<Talk> talks, int current){
		if(current == talks.size()){
			return true;
		}
		
		Talk talk = talks.get(current);
		for(int i = 0; i < sessions.size(); i++){
			if(sessions.get(i).addTalk(talk)){
				sessionTalkMatch.get(i)[current] = true;
				return schedule(talks, current + 1);
			}
		}
		Session newSession = new Session();
		newSession.addTalk(talk);
		sessions.add(newSession);
		sessionTalkMatch.get(sessions.size() - 1)[current] = true;
		return true;
	}
	
	public int getWasted() {
		int sum = 0;
		for (Session session : sessions) {
			sum += session.getWastedTime();
		}
		return sum;
	}

	public List<Session> getSessions() {
		return sessions;
	}
	
	public String ConferenceToString(){
		StringBuilder sb = new StringBuilder();
		for (Session session : sessions) {
			int track = 0;
			sb.append("Track ").append(track).append(":");
			for(Talk talk: session.getMorningTalks()){
				sb.append(talk.getTime()).append(": ").append(talk.getTitle());
			}
			sb.append("12:00 Lunch");
			for(Talk talk: session.getEveningTalks()){
				sb.append(talk.getTime()).append(": ").append(talk.getTitle());
			}
			sb.append(session.getTimeNetworking()).append(": Networking");
			track++;
		}
		return sb.toString();
	}
}
