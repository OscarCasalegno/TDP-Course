package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {
	private Graph<Actor, DefaultWeightedEdge> grafo;
	private Map<Integer, Actor> idAttori;
	private Simulator sim;

	public List<String> getGeneri() {

		return ImdbDAO.getGeneri();
	}

	public void creaGrafo(String genere) {

		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		List<Actor> attori = ImdbDAO.getAttoriPerGenere(genere);

		Graphs.addAllVertices(this.grafo, attori);

		this.idAttori = new HashMap<>();
		for (Actor a : attori) {
			this.idAttori.put(a.getId(), a);
		}

		List<Adiacenze> ad = ImdbDAO.getAdiacenze(idAttori, genere);

		for (Adiacenze a : ad) {
			DefaultWeightedEdge e = this.grafo.addEdge(a.getAttore1(), a.getAttore2());
			this.grafo.setEdgeWeight(e, a.getPeso());
		}
	}

	public List<Actor> getAttori() {

		List<Actor> attori = new ArrayList<>(this.grafo.vertexSet());

		attori.sort(null);

		return attori;

	}

	public List<Actor> getConnessi(Actor attore) {
		ConnectivityInspector<Actor, DefaultWeightedEdge> con = new ConnectivityInspector<>(this.grafo);
		List<Actor> attori = new ArrayList<>(con.connectedSetOf(attore));
		attori.sort(null);
		attori.remove(attore);
		return attori;
	}

	public void simula(Integer giorni) {
		sim = new Simulator();
		sim.init(grafo);
		sim.setNumeroGiorni(giorni);
		sim.run();
	}

	public Integer getGiorniPausa() {
		return sim.getNumeroPause();
	}

	public List<Actor> getIntervistati() {
		return sim.getIntervistati();
	}

	public boolean isReady() {
		if (this.grafo != null)
			return true;
		return false;
	}

}
