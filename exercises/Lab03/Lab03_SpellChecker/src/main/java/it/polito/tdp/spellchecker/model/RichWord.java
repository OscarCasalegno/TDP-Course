package it.polito.tdp.spellchecker.model;

import java.beans.Beans;

public class RichWord extends Beans {

    private String parola;
    private boolean corretta;

    public RichWord(String parola, boolean corretta) {
        this.parola = parola;
        this.corretta = corretta;
    }

    /**
     * @return the corretta
     */
    public boolean isCorretta() {
        return corretta;
    }

    /**
     * @return the parola
     */
    public String getParola() {
        return parola;
    }

}
