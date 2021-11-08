package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Simulator {

	// Costanti
	private static final int NUM_TAB_10 = 2;
	private static final int NUM_TAB_8 = 4;
	private static final int NUM_TAB_6 = 4;
	private static final int NUM_TAB_4 = 5;
	private static final int NUM_CLIENTS = 2000;
	private static final LocalTime OPENING_Hour = LocalTime.of(7, 00);
	private static final LocalTime CLOSING_Hour = LocalTime.of(19, 00);

	// Strutture dati
	private List<Table> tables;
	private PriorityQueue<Event> queue;

	// Valori da calcolare
	private int numClienti;
	private int numSoddisfatti;
	private int numInsoddisfatti;

	// valore di servizio
	private int count;

	public Simulator() {
		this.tables = new ArrayList<>();
		this.queue = new PriorityQueue<Event>();
	}

	public void run() {
		// inizializzazione variabili
		this.numClienti = 0;
		this.numInsoddisfatti = 0;
		this.numSoddisfatti = 0;
		this.count = 0;

		this.tables.clear();
		this.queue.clear();

		// mondo
		for (int i = 0; i < NUM_TAB_10; i++)
			tables.add(new Table(10));

		for (int i = 0; i < NUM_TAB_8; i++)
			tables.add(new Table(8));

		for (int i = 0; i < NUM_TAB_6; i++)
			tables.add(new Table(6));

		for (int i = 0; i < NUM_TAB_4; i++)
			tables.add(new Table(4));

		this.tables.sort(null);

		// coda
		Event start = new NewGroupEvent(LocalTime.of(7, 00));

		queue.add(start);

		// simulazione

		while (count < this.NUM_CLIENTS) {

			this.processEvent(this.queue.poll());

		}

	}

	private void processEvent(Event poll) {

		if (poll instanceof NewGroupEvent) {
			// nuovo evento clienti, aumento contatore
			this.count++;

			NewGroupEvent e = (NewGroupEvent) poll;
			this.numClienti += e.getNumberOfClients();

			// controllo tavoli liberi
			for (Table t : this.tables) {
				if (t.isFree() && t.getSeats() >= e.getNumberOfClients()
						&& t.getSeats() / 2 <= e.getNumberOfClients()) {
					// tavolo disponibile
					this.numSoddisfatti += e.getNumberOfClients();
					t.setFree(false);
					this.queue.add(new FreeTableEvent(e.getTime().plus(e.getPermanence()), t));
					break;
				}
			}

			if (!e.isSeated()) {
				// nessun tavolo disponibile, tentativo di mandare al bancone
				Double rand = Math.random();
				if (rand < e.getTolerance()) {
					// tentativo riuscito, clienti soddisfatti
					this.numSoddisfatti += e.getNumberOfClients();
				} else {
					// tentativo non riuscito, clienti non soddisfatti
					this.numInsoddisfatti += e.getNumberOfClients();
				}
			}

			// genero nuovo evento di arrivo clienti
			Duration rand = Duration.of((long) (Math.random() * 10 + 1), ChronoUnit.MINUTES);

			Event next = new NewGroupEvent(e.getTime().plus(rand));

			if (next.getTime().isAfter(CLOSING_Hour)) {
				next.getTime().plus(Duration.of(12, ChronoUnit.HOURS));
			}

			this.queue.add(next);

		}

		if (poll instanceof FreeTableEvent) {
			// si libera il tavolo
			((FreeTableEvent) poll).getTable().setFree(true);
		}

	}

	public int getNumClienti() {
		return numClienti;
	}

	public int getNumSoddisfatti() {
		return numSoddisfatti;
	}

	public int getNumInsoddisfatti() {
		return numInsoddisfatti;
	}

}
