package it.polito.tdp.parole.model;

import java.util.List;

public class Parole {

	protected List<String> parole;
	
	public void addParola(String p) {
		parole.add(p);
	}
	
	public List<String> getElenco() {
		parole.sort(null);
		return parole;
	}
	
	public void reset() {
		parole.clear();
	}

	public void delParola(String selezionato) {
		// TODO Auto-generated method stub
		parole.remove(selezionato);
	}
	
}
