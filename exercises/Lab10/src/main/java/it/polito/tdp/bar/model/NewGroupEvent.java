package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class NewGroupEvent extends Event {

	private int numberOfClients;
	private Duration permanence;
	private Double tolerance;
	private boolean seated;

	/**
	 * @param time
	 */
	public NewGroupEvent(LocalTime time) {
		super(time);
		Random rand = new Random();
		this.numberOfClients = (int) (10 * rand.nextDouble() + 1);
		double num;
		do {
			num = rand.nextDouble();
		} while (num > 0.9);
		this.tolerance = num;

		num = rand.nextInt(61);

		this.permanence = Duration.of((long) (60 + num), ChronoUnit.MINUTES);

		this.seated = false;

	}

	public int getNumberOfClients() {
		return numberOfClients;
	}

	public Duration getPermanence() {
		return permanence;
	}

	public Double getTolerance() {
		return tolerance;
	}

	public boolean isSeated() {
		return seated;
	}

	public void setSeated(boolean seated) {
		this.seated = seated;
	}

}
