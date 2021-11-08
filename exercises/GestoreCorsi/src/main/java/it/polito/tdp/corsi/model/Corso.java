package it.polito.tdp.corsi.model;

public class Corso {

    private String codins;
    private Integer crediti;
    private String nome;
    private Integer pd;

    /**
     * @param codins
     * @param crediti
     * @param nome
     * @param pd
     */
    public Corso(String codins, Integer crediti, String nome, Integer pd) {
        super();
        this.codins = codins;
        this.crediti = crediti;
        this.nome = nome;
        this.pd = pd;
    }

    /**
     * @return the codIns
     */
    public String getCodins() {
        return codins;
    }

    /**
     * @param codIns the codIns to set
     */
    public void setCodins(String codIns) {
        this.codins = codIns;
    }

    /**
     * @return the crediti
     */
    public Integer getCrediti() {
        return crediti;
    }

    /**
     * @param crediti the crediti to set
     */
    public void setCrediti(Integer crediti) {
        this.crediti = crediti;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the pd
     */
    public Integer getPd() {
        return pd;
    }

    /**
     * @param pd the pd to set
     */
    public void setPd(Integer pd) {
        this.pd = pd;
    }

    @Override
    public String toString() {
        return "Corso [codins=" + codins + ", crediti=" + crediti + ", nome=" + nome + ", pd=" + pd
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codins == null) ? 0 : codins.hashCode());
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
        Corso other = (Corso) obj;
        if (codins == null) {
            if (other.codins != null) {
                return false;
            }
        } else if (!codins.equals(other.codins)) {
            return false;
        }
        return true;
    }

}
