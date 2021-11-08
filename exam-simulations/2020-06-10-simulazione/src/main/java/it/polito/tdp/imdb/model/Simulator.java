package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.imdb.model.Event.EventType;

public class Simulator {

	// TODO PARAMETRI DI SIMULAZIONE
	private Integer numeroGiorni = 1;
	private final Double PROBABILITA_CASUALE = 0.6;
	private final Double PROBABILITA_CONSIGLIO = 0.4;
	private final Double PROBABILITA_PAUSA = 0.9;

	// TODO OUTPUT DA CALCOLARE
	private Integer numeroPause;
	private List<Actor> intervistati;

	// TODO STATO DEL SISTEMA
	private String lastGender;
	private Integer consecutivi;
	private Graph<Actor, DefaultWeightedEdge> grafo;

	// TODO CODA DEGLI EVENTI
	private PriorityQueue<Event> queue;

	// TODO INIZIALIZZAZIONE
	public void init(Graph<Actor, DefaultWeightedEdge> grafo) {
		// coda
		this.queue = new PriorityQueue<>();

		// stato del sistema
		this.consecutivi = 0;
		this.grafo = grafo;
		this.lastGender = "";

		// output
		this.numeroPause = 0;
		this.intervistati = new ArrayList<>();

		// generiamo eventi iniziali

		queue.add(new Event(0, EventType.ESTRAZIONE_CASUALE, null));

	}

	// TODO getter output

	public Integer getNumeroPause() {
		return numeroPause;
	}

	public List<Actor> getIntervistati() {
		return intervistati;
	}

	// TODO ESECUZIONE
	public void run() {
		Event e;
		do {
			e = this.queue.poll();
			processEvent(e);
		} while (e.getGiorno() < this.numeroGiorni);
	}

	private void processEvent(Event e) {

		switch (e.getTipo()) {

		case INTERVISTA:
			intervistati.add(e.getAttore());
			if (this.lastGender.contentEquals(e.getAttore().getGender())) {
				this.consecutivi++;
			} else {
				this.consecutivi = 0;
				this.lastGender = e.getAttore().getGender();
			}
			Boolean pausa = false;
			if (this.consecutivi == 2) {
				pausa = true;
				this.consecutivi = 0;
				this.lastGender = "";
			}

			if (Math.random() < this.PROBABILITA_PAUSA && pausa) {
				queue.add(new Event(e.getGiorno() + 1, EventType.PAUSA, null));
			} else {
				pausa = false;
			}

			if (Math.random() < this.PROBABILITA_CASUALE && !pausa) {
				queue.add(new Event(e.getGiorno() + 1, EventType.ESTRAZIONE_CASUALE, null));
			} else if (!pausa) {
				queue.add(new Event(e.getGiorno() + 1, EventType.CONSIGLIO, e.getAttore()));
			}
			break;

		case ESTRAZIONE_CASUALE:
			Random rand = new Random();
			List<Actor> attori = new ArrayList<>(this.grafo.vertexSet());
			Integer i;
			do {
				i = rand.nextInt(attori.size());
			} while (intervistati.contains(attori.get(i)));

			queue.add(new Event(e.getGiorno(), EventType.INTERVISTA, attori.get(i)));
			break;

		case CONSIGLIO:
			Actor a = this.getConsiglio(e.getAttore());
			if (a == null) {
				queue.add(new Event(e.getGiorno(), EventType.ESTRAZIONE_CASUALE, null));
			} else {
				queue.add(new Event(e.getGiorno(), EventType.INTERVISTA, a));
			}
			break;

		case PAUSA:
			this.numeroPause++;
			queue.add(new Event(e.getGiorno() + 1, EventType.ESTRAZIONE_CASUALE, null));
			break;
		}
	}

	private Actor getConsiglio(Actor attore) {
		Actor best = null;
		Double bestP = 0.0;
		for (Actor a : Graphs.neighborListOf(this.grafo, attore)) {
			Double peso = this.grafo.getEdgeWeight(this.grafo.getEdge(attore, a));
			if (peso > bestP && !intervistati.contains(a)) {
				best = a;
				bestP = peso;
			}
		}
		return best;
	}

	// TODO parametri della simulazione modificabili

	public Integer getNumeroGiorni() {
		return numeroGiorni;
	}

	public void setNumeroGiorni(Integer numeroGiorni) {
		this.numeroGiorni = numeroGiorni;
	}

}
