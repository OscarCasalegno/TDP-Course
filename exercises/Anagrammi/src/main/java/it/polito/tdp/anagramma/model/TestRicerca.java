package it.polito.tdp.anagramma.model;

import java.util.List;

public class TestRicerca {

    public static void main(String[] args) {
        Ricerca ric = new Ricerca();

        List<String> ana_dog = ric.anagrammi("dog");

        System.out.println(ana_dog);

        List<String> ana_vac = ric.anagrammi("vacanze");

        System.out.println(ana_vac);

        List<String> ana_prog = ric.anagrammi("programmazione");

        System.out.println(ana_prog);

        // TODO controllare che gli anagrammi siano parole valide (controllo dizionario
        // alla fine o durante)

        // TODO non ripetere anagrammi uguali(lettere doppie scambiate) controllando
        // alla fine se duplicate o dando un vincolo di posizione alle due lettere
    }
}
