package it.polito.tdp.bar.model;

public class Model {

	Simulator sim;

	public Model() {
		this.sim = new Simulator();
	}

	public void simula() {
		sim.run();
	}

	public int getNumClienti() {
		return this.sim.getNumClienti();
	}

	public int getNumSoddisfatti() {
		return this.sim.getNumSoddisfatti();
	}

	public int getNumInsoddisfatti() {
		return this.sim.getNumInsoddisfatti();
	}

}
