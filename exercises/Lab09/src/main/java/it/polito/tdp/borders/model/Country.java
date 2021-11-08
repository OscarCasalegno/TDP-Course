package it.polito.tdp.borders.model;

public class Country {

	private String abbreviazione;
	private int codice;
	private String nome;

	/**
	 * @param abbreviazione
	 * @param codice
	 * @param nome
	 */
	public Country(String abbreviazione, int codice, String nome) {
		super();
		this.abbreviazione = abbreviazione;
		this.codice = codice;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return abbreviazione + " " + codice + " " + nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abbreviazione == null) ? 0 : abbreviazione.hashCode());
		result = prime * result + codice;
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
		Country other = (Country) obj;
		if (abbreviazione == null) {
			if (other.abbreviazione != null) {
				return false;
			}
		} else if (!abbreviazione.equals(other.abbreviazione)) {
			return false;
		}
		if (codice != other.codice) {
			return false;
		}
		return true;
	}

	/**
	 * @return the abbreviazione
	 */
	public String getAbbreviazione() {
		return abbreviazione;
	}

	/**
	 * @param abbreviazione the abbreviazione to set
	 */
	public void setAbbreviazione(String abbreviazione) {
		this.abbreviazione = abbreviazione;
	}

	/**
	 * @return the codice
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * @param codice the codice to set
	 */
	public void setCodice(int codice) {
		this.codice = codice;
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

}
