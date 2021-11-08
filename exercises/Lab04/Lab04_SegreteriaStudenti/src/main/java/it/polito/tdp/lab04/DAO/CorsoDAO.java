package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

    /*
     * Ottengo tutti i corsi salvati nel Db
     */
    public List<Corso> getTuttiICorsi() {

        final String sql = "SELECT * FROM corso";

        List<Corso> corsi = new LinkedList<Corso>();

        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                String codins = rs.getString("codins");
                int numeroCrediti = rs.getInt("crediti");
                String nome = rs.getString("nome");
                int periodoDidattico = rs.getInt("pd");

                System.out.println(
                        codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

                // Crea un nuovo JAVA Bean Corso
                // Aggiungi il nuovo oggetto Corso alla lista corsi
                corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
            }

            conn.close();

            return corsi;

        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException("Errore Db", e);
        }
    }

    /*
     * Dato un codice insegnamento, ottengo il corso
     */
    public void getCorso(Corso corso) {
        // TODO
    }

    /*
     * Ottengo tutti gli studenti iscritti al Corso
     */
    public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {

        final String sql = "SELECT s.matricola,s.cognome,s.nome,s.CDS FROM studente AS s, iscrizione AS i WHERE i.matricola=s.matricola AND i.codins=?";

        List<Studente> studenti = new LinkedList<>();

        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, corso.getId());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int matr = rs.getInt("matricola");
                String cognome = rs.getString("cognome");
                String nome = rs.getString("nome");
                String cds = rs.getString("CDS");
                studenti.add(new Studente(matr, cognome, nome, cds));

            }

            conn.close();

            return studenti;

        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException("Errore Db", e);
        }

    }

    /*
     * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
     */
    public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
        final String sql = "INSERT INTO  iscrizione(matricola,codins) VALUES (?, ?)";

        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, studente.getMatricola());
            st.setString(2, corso.getId());

            int rs = st.executeUpdate();

            conn.close();

            if (rs == 0) {
                return false;
            } else {
                return true;
            }

        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException("Errore Db", e);

        }
    }

}
