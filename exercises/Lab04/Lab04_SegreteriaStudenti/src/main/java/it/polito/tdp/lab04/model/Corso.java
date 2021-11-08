package it.polito.tdp.lab04.model;

public class Corso {

    private String id;
    private Integer crediti;
    private String nome;
    private Integer pd;

    /**
     * @param id
     * @param crediti
     * @param nome
     * @param pd
     */
    public Corso(String id, Integer crediti, String nome, Integer pd) {
        super();
        this.id = id;
        this.crediti = crediti;
        this.nome = nome;
        this.pd = pd;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " " + nome;
    }

}
