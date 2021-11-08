package it.polito.tdp.imdb.model;

public class Adiacenze {

	private Actor attore1;
	private Actor attore2;
	private Integer peso;

	/**
	 * @param attore1
	 * @param attore2
	 * @param peso
	 */
	public Adiacenze(Actor attore1, Actor attore2, Integer peso) {
		super();
		this.attore1 = attore1;
		this.attore2 = attore2;
		this.peso = peso;
	}

	public Actor getAttore1() {
		return attore1;
	}

	public void setAttore1(Actor attore1) {
		this.attore1 = attore1;
	}

	public Actor getAttore2() {
		return attore2;
	}

	public void setAttore2(Actor attore2) {
		this.attore2 = attore2;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

}
