package com.thoughtworks.trackmanagement.model;

public class MiscEvent implements Event {

	private String title;
	private int time;
	private int startTime;

	public MiscEvent() {
		super();
	}

	public MiscEvent(String title, int time, int startTime) {
		super();
		this.title = title;
		this.time = time;
		this.startTime = startTime;
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

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int compareTo(Talk o) {
		return ((Integer) o.getTime()).compareTo(time);
	}

	public String toString() {
		return title;
	}
}
