package it.polito.tdp.extflightdelays.model;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	Graph<Airport, DefaultWeightedEdge> graph;

	public void creaGrafo() {
		graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		// aggiungo i vertici
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		Graphs.addAllVertices(graph, dao.loadAllAirports());

		System.out.println(graph.vertexSet().size());
	}
}
