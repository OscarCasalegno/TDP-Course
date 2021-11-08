package it.polito.tdp.bar.model;

public class Table implements Comparable<Table> {

	private boolean free;
	public Integer seats;

	/**
	 * @param seats
	 */
	public Table(int seats) {
		this.free = true;
		this.seats = seats;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	@Override
	public int compareTo(Table o) {
		return this.seats.compareTo(o.getSeats());
	}

}
