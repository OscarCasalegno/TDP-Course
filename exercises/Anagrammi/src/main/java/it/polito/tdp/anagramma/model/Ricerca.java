package it.polito.tdp.anagramma.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {

    private List<String> soluzione;

    /**
     * Genera tutti gli anagrammi della parola specificata in ingresso.
     * 
     * @param parola la parola da anagrammare
     * @return lista di tutti gli anagrammi della parola data
     */
    public List<String> anagrammi(String parola) {
        this.soluzione = new ArrayList<>();

        parola = parola.toUpperCase();

        List<Character> disponibili = new ArrayList<Character>();
        for (int i = 0; i < parola.length(); i++) {
            disponibili.add(parola.charAt(i));
        }

        // avvia la ricorsione
        this.cerca("", 0, disponibili);
        return this.soluzione;
    }

    /**
     * Procedura ricorsiva per il calcolo dell'anagramma.
     * 
     * @param parziale    parte iniziale dell'anagramma costruito fin'ora
     * @param livello     livello della ricorsione, sempre uguale a
     *                    {@code parziale.length()}
     * @param disponibili elenco delle lettere non ancora utilizzate
     */
    private void cerca(String parziale, int livello, List<Character> disponibili) {

        if (disponibili.size() == 0) {
            // caso terminale
            this.soluzione.add(parziale);
        }

        // caso normale
        // provare ad aggiungere a 'parziale' tutti i caratteri presenti tra i
        // disponibili
        for (Character ch : disponibili) {
            String tentativo = parziale + ch;
            List<Character> rimanenti = new ArrayList<>(disponibili);
            rimanenti.remove(ch);
            this.cerca(tentativo, livello + 1, rimanenti);
        }

    }

}
/*
 * Dato di partenza: parola da anagrammare di lunghezza N.
 * 
 * Soluzione parziale: una parte dell'anagramma già costruito (i primi
 * caratteri).
 * 
 * Livello: numero di lettere di cui è composta la soluzione parziale.
 * 
 * Soluzione finale: soluzione di lunghezza N -> caso terminale.
 * 
 * Caso terminale: salvare la soluzione trovata.
 * 
 * Generazione nuove soluzioni: provare ad aggiungere una lettera scegliendola
 * tra quelle non ancora utilizzate.
 */
