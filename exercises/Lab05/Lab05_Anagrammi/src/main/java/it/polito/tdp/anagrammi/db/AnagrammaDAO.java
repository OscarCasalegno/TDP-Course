package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.anagrammi.model.Anagramma;

public class AnagrammaDAO {

    /**
     * Verifica se la stringa passata come parametro è presente nel DB.
     * 
     * @param stringa la stringa da verificare
     * @return {@code true} se presente, {@code false} altrimenti
     */
    public static boolean isCorretto(String stringa) {

        String sql = "SELECT * " + "FROM parola AS p " + "WHERE p.nome=?";

        try {

            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, stringa);

            ResultSet rs = st.executeQuery();

            conn.close();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifica se l'{@link Anagramma} passato come parametro è presente nel DB.
     * 
     * @param anagramma l'{@link Anagramma} da verificare
     * @return {@code true} se presente, {@code false} altrimenti
     */
    public static boolean isCorretto(Anagramma anagramma) {

        String sql = "SELECT * " + "FROM parola AS p " + "WHERE p.nome= ? ";

        try {

            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = conn.prepareCall(sql);
            st.setString(1, anagramma.getAnagramma());

            ResultSet rs = st.executeQuery();

            conn.close();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
