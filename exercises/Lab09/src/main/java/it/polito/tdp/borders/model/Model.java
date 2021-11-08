package it.polito.tdp.borders.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private Map<String, Country> countryIdMap;
	private Graph<Country, DefaultEdge> grafo;

	public Model() {
		this.countryIdMap = new HashMap<>();
	}

	public void creaGrafo(int anno) {
		this.grafo = new SimpleGraph<>(DefaultEdge.class);

		BordersDAO dao = new BordersDAO();
		dao.loadAllCountries(this.countryIdMap);

		// Vertici
		Graphs.addAllVertices(this.grafo, this.countryIdMap.values());

		// Lati
		List<Border> confini = dao.getCountryPairs(anno, this.countryIdMap);
		for (Border b : confini) {
			this.grafo.addEdge(b.getC1(), b.getC2());
		}

	}

	public int vertexNum() {
		return this.grafo.vertexSet().size();
	}

	public int edgeNum() {
		return this.grafo.edgeSet().size();
	}

	public int compConn() {
		ConnectivityInspector<Country, DefaultEdge> connIns = new ConnectivityInspector<>(grafo);

		return connIns.connectedSets().size();
	}

	public Map<Country, Integer> paesi() {
		Map<Country, Integer> paesi = new HashMap<>();

		for (Country c : this.grafo.vertexSet()) {
			paesi.put(c, this.grafo.degreeOf(c));
		}

		return paesi;
	}

	public Collection<Country> getAllCountries() {

		BordersDAO dao = new BordersDAO();
		dao.loadAllCountries(this.countryIdMap);

		return this.countryIdMap.values();

	}

	public Set<Country> getVicini(Country co) {

		Set<Country> vicini = new HashSet<>();
		Ricorsione.trova(this.grafo, co, vicini);

		if (vicini.size() == 0)
			return null;

		return vicini;

	}
}
