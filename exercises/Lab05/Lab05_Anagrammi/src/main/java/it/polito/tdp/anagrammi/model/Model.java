package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.anagrammi.db.AnagrammaDAO;

public class Model {

    List<Anagramma> soluzione;

    public List<Anagramma> calcolaAnagrammi(String parola) {
        List<Character> pool = new ArrayList<>();
        soluzione = new ArrayList<>();
        for (int i = 0; i < parola.length(); i++) {
            pool.add(parola.charAt(i));
        }
        this.cerca("", 0, pool);
        return soluzione;
    }

    private void cerca(String parziale, int livello, List<Character> pool) {

        if (pool.size() == 0) {
            this.soluzione.add(new Anagramma(parziale, AnagrammaDAO.isCorretto(parziale)));
        }

        for (Character ch : pool) {
            String prova = parziale + ch;
            List<Character> poolRicorsione = new ArrayList<>(pool);
            poolRicorsione.remove(ch);
            this.cerca(prova, livello + 1, poolRicorsione);
        }

    }

}
