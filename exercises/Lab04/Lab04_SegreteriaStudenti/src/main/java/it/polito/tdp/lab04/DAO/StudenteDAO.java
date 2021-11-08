package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

    public List<Studente> getTuttiGliStudenti() {

        final String sql = "SELECT * FROM studente";

        List<Studente> studenti = new LinkedList<>();

        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int matricola = rs.getInt("matricola");
                String cognome = rs.getString("cognome");
                String nome = rs.getString("nome");
                String cds = rs.getString("CDS");
                studenti.add(new Studente(matricola, cognome, nome, cds));
            }

            conn.close();

            return studenti;

        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException("Errore Db", e);
        }
    }

    public Studente getStudenteDaMatricola(Integer matricola) {

        final String sql = "SELECT * FROM studente WHERE matricola= ?";

        Studente studente = null;

        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, matricola);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                int matr = rs.getInt("matricola");
                String cognome = rs.getString("cognome");
                String nome = rs.getString("nome");
                String cds = rs.getString("CDS");
                studente = new Studente(matr, cognome, nome, cds);
            }

            conn.close();

            return studente;

        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException("Errore Db", e);
        }
    }

    public List<Corso> getCorsiStudente(Studente stud) {
        final String sql = "SELECT c.codins,c.crediti,c.nome,c.pd FROM corso AS c, iscrizione AS i WHERE i.codins=c.codins AND i.matricola=?";

        List<Corso> corsi = new LinkedList<Corso>();

        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, stud.getMatricola());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                String codins = rs.getString("codins");
                int numeroCrediti = rs.getInt("crediti");
                String nome = rs.getString("nome");
                int periodoDidattico = rs.getInt("pd");

                corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
            }

            conn.close();

            return corsi;

        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException("Errore Db", e);
        }
    }

}
