package com.thoughtworks.trackmanagement.model;

public class TalkAllocation implements Comparable<TalkAllocation>{

	Talk talk;
	private int start;
	
	public Talk getTalk() {
		return talk;
	}

	public void setTalk(Talk talk) {
		this.talk = talk;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public TalkAllocation() {
		super();
	}

	public int compareTo(TalkAllocation o) {
		return ((Integer)this.getStart()).compareTo(o.getStart());
	}
	
}
