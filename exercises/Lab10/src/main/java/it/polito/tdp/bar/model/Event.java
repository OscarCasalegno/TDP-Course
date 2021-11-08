package it.polito.tdp.bar.model;

import java.time.LocalTime;

public class Event implements Comparable<Event> {

	private LocalTime time;

	/**
	 * @param time
	 * @param type
	 */
	public Event(LocalTime time) {
		super();
		this.time = time;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Override
	public int compareTo(Event o) {
		return this.time.compareTo(o.getTime());
	}

}
