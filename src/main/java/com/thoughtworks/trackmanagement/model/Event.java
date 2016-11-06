package com.thoughtworks.trackmanagement.model;

public interface Event {

	public String getTitle();
	
	public int getTime();
	
	public int getStartTime();
	
	public void setTitle(String title);
	
	public void setTime(int time);
	
	public void setStartTime(int startTime);
}
