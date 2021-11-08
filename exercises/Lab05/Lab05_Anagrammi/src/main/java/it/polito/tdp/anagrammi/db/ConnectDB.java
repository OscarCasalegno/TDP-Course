package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    private static final String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL);
    }

}
