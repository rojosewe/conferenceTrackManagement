package com.thoughtworks.trackmanagement.planner;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.trackmanagement.exception.InvalidTalkSpecsException;
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
 */
public class ConferencePlanner {

	ArrayList<Session> sessions = new ArrayList<Session>();
	ArrayList<Boolean[]> sessionTalkMatch = new ArrayList<Boolean[]>();

	public boolean buildSessions(List<Talk> talks) throws InvalidTalkSpecsException {
		Collections.sort(talks);
		if(talks.size() == 0){
			return true;
		}
		if(!new Session().addTalk(talks.get(0))){
			throw new InvalidTalkSpecsException("The largest talk does not fit into "
					+ "a day's session.");
		}
		addNewSession(talks);
		return schedule(talks, 0);
		
	}
	
	private boolean schedule(List<Talk> talks, int current){
		if(current == talks.size()){
			return true;
		}
		
		Talk talk = talks.get(current);
		int minWaste = Integer.MAX_VALUE;
		int best = -1;
		best = getBestStrategy(talk, minWaste, best);
		if(best == -1){
			addNewSession(talks);
			best = sessions.size() - 1;
		}
		sessions.get(best).addTalk(talk);
		sessionTalkMatch.get(best)[current] = true;
		return schedule(talks, current + 1);
	}

	private int getBestStrategy(Talk talk, int minWaste, int best) {
		for(int i = 0; i < sessions.size(); i++){
			int waste = sessions.get(i).getMinimumWasteAfterAdd(talk);
			if(waste != -1 && waste < minWaste){
				best = i;
			}
		}
		return best;
	}
	
	private void addNewSession(List<Talk> talks) {
		Session session = new Session();
		sessions.add(session);
		sessionTalkMatch.add(new Boolean[talks.size()]);
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
	
}
