package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectDB {

    private static final String jdbcURL = "jdbc:mysql://localhost/iscritticorsi?user=root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL);
    }
}
