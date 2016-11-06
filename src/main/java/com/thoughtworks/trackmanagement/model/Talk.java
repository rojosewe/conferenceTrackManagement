package com.thoughtworks.trackmanagement.model;

public class Talk implements Comparable<Talk> {
	
	private String title;
	private int time;
	
	public Talk() {
		super();
	}
	
	public Talk(String title, int time) {
		super();
		this.title = title;
		this.time = time;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int compareTo(Talk o) {
		return ((Integer)o.getTime()).compareTo(time);
	}
}
