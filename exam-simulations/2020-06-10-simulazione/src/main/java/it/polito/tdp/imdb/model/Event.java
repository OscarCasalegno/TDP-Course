package it.polito.tdp.imdb.model;

public class Event implements Comparable<Event> {

	public enum EventType {
		INTERVISTA, ESTRAZIONE_CASUALE, CONSIGLIO, PAUSA
	}

	private Integer giorno;
	private EventType tipo;
	private Actor attore;

	/**
	 * @param giorno
	 * @param tipo
	 * @param attore
	 */
	public Event(Integer giorno, EventType tipo, Actor attore) {
		super();
		this.giorno = giorno;
		this.tipo = tipo;
		this.attore = attore;
	}

	public Integer getGiorno() {
		return giorno;
	}

	public EventType getTipo() {
		return tipo;
	}

	public Actor getAttore() {
		return attore;
	}

	@Override
	public int compareTo(Event other) {

		return this.giorno.compareTo(other.giorno);
	}

}
