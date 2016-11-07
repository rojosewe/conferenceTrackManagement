package com.thoughtworks.trackmanagement.model;

import java.util.List;

public interface TalkSet{
	
	public List<Talk> getTalks();

	public int getTime();
	
	public int getWasteAfterAdd(Talk talk);

	public boolean addTalk(Talk talk);

	public boolean removeTalk(Talk talk);

	public int getWastedTime();
	
	public MiscEvent getPostEvent();

	public List<Event> getEvents();
}