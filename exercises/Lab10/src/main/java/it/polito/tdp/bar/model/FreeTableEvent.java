package it.polito.tdp.bar.model;

import java.time.LocalTime;

public class FreeTableEvent extends Event {

	Table table;

	/**
	 * @param time
	 * @param table
	 */
	public FreeTableEvent(LocalTime time, Table table) {
		super(time);
		this.table = table;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

}
