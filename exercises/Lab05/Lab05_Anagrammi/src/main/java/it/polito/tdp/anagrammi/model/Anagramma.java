package it.polito.tdp.anagrammi.model;

/**
 * Classe Java Bean degli anagrammi
 * 
 * @author Oscar
 *
 */
public class Anagramma {

    private String anagramma;
    private boolean corretto;

    /**
     * @param anagramma
     * @param corretto
     */
    public Anagramma(String anagramma, boolean corretto) {
        super();
        this.anagramma = anagramma;
        this.corretto = corretto;
    }

    /**
     * @return the anagramma
     */
    public String getAnagramma() {
        return anagramma;
    }

    /**
     * @param anagramma the anagramma to set
     */
    public void setAnagramma(String anagramma) {
        this.anagramma = anagramma;
    }

    /**
     * @return the corretto
     */
    public boolean isCorretto() {
        return corretto;
    }

    /**
     * @param corretto the corretto to set
     */
    public void setCorretto(boolean corretto) {
        this.corretto = corretto;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((anagramma == null) ? 0 : anagramma.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Anagramma other = (Anagramma) obj;
        if (anagramma == null) {
            if (other.anagramma != null) {
                return false;
            }
        } else if (!anagramma.equals(other.anagramma)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return anagramma;
    }

}
