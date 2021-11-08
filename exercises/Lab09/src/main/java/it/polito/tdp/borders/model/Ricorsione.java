package it.polito.tdp.borders.model;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;

public class Ricorsione {

	public static void trova(Graph<Country, DefaultEdge> grafo, Country co, Set<Country> vicini) {

		cerca(grafo, co, vicini);

	}

	private static void cerca(Graph<Country, DefaultEdge> grafo, Country co, Set<Country> vicini) {

		for (Country c : Graphs.neighborSetOf(grafo, co)) {
			if (!vicini.contains(co)) {
				vicini.add(c);
				cerca(grafo, c, vicini);
			}
		}

	}

}
