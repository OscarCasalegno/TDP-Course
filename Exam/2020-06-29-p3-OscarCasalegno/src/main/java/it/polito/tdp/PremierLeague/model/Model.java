package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {

	private Graph<Match, DefaultWeightedEdge> graph;
	private PremierLeagueDAO dao = new PremierLeagueDAO();
	private Map<String, Integer> mesi;
	private Map<Integer, Match> idMapMatch;
	private List<Match> percorso;
	private Double pesoMax;

	public Model() {

		mesi = new HashMap<>();

		mesi.put("Gennaio", 1);
		mesi.put("Febbraio", 2);
		mesi.put("Marzo", 3);
		mesi.put("Aprile", 4);
		mesi.put("Maggio", 5);
		mesi.put("Giugno", 6);
		mesi.put("Luglio", 7);
		mesi.put("Agosto", 8);
		mesi.put("Settembre", 9);
		mesi.put("Ottobre", 10);
		mesi.put("Novembre", 11);
		mesi.put("Dicembre", 12);
	}

	public void creaGrafo(String mese, Integer min) {

		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		this.idMapMatch = new HashMap<>();

		Integer m = this.mesi.get(mese);

		Graphs.addAllVertices(this.graph, dao.getVertici(m, idMapMatch));

		for (Adiacenza a : dao.getAdiacenze(min, idMapMatch)) {
			if (this.graph.containsVertex(a.getM1()) && this.graph.containsVertex(a.getM2())) {
				Graphs.addEdge(this.graph, a.getM1(), a.getM2(), a.getPeso());
			}

		}

	}

	public Integer getNumeroVertici() {
		return this.graph.vertexSet().size();
	}

	public Integer getNumeroArchi() {
		return this.graph.edgeSet().size();
	}

	public List<Adiacenza> getConnessioneMassima() {

		if (this.graph == null) {
			return null;
		}

		List<Adiacenza> list = new ArrayList<>();
		Double bestP = 0.0;

		for (DefaultWeightedEdge e : this.graph.edgeSet()) {
			if (bestP == this.graph.getEdgeWeight(e)) {
				list.add(new Adiacenza(this.graph.getEdgeTarget(e), this.graph.getEdgeSource(e),
						this.graph.getEdgeWeight(e)));
			} else if (bestP < this.graph.getEdgeWeight(e)) {
				list.clear();
				list.add(new Adiacenza(this.graph.getEdgeTarget(e), this.graph.getEdgeSource(e),
						this.graph.getEdgeWeight(e)));
				bestP = this.graph.getEdgeWeight(e);
			}
		}

		return list;

	}

	public Set<Match> getVertici() {

		return this.graph.vertexSet();
	}

	public void cercaCollegamento(Match m1, Match m2) {

		this.percorso = null;
		this.pesoMax = 0.0;

		List<Match> parziale = new ArrayList<>();
		parziale.add(m1);

		this.trova(parziale, m2);

	}

	private void trova(List<Match> parziale, Match m2) {

		if (parziale.get(parziale.size() - 1).equals(m2)) {
			if (this.pesoMax < this.calcolaPeso(parziale)) {
				this.percorso = new ArrayList<>(parziale);
				this.pesoMax = this.calcolaPeso(parziale);
			}
			return;
		}

		for (Match m : Graphs.neighborListOf(this.graph, parziale.get(parziale.size() - 1))) {
			Match last = parziale.get(parziale.size() - 1);
			if (!parziale.contains(m) && this.isValido(m, last)) {
				parziale.add(m);
				this.trova(parziale, m2);
				parziale.remove(parziale.size() - 1);
			}

		}

	}

	private boolean isValido(Match m, Match last) {

		if (m.getTeamAwayID().equals(last.getTeamHomeID()) && m.getTeamHomeID().equals(last.getTeamAwayID())) {
			return false;
		}

		return true;
	}

	private Double calcolaPeso(List<Match> parziale) {
		Double somma = 0.0;

		for (int i = 0; i < parziale.size() - 1; i++) {
			somma += this.graph.getEdgeWeight(this.graph.getEdge(parziale.get(i), parziale.get(i + 1)));
		}

		return somma;
	}

	public List<Match> getPercorso() {
		return percorso;
	}

	public Double getPesoMax() {
		return pesoMax;
	}

}
