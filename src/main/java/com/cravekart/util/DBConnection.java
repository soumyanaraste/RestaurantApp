package com.cravekart.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
        "jdbc:mysql://localhost:3306/cravekart";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "Ankita@2001";

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found!", e);
        }

        Connection connection = DriverManager.getConnection(
            URL, USERNAME, PASSWORD
        );

        System.out.println("CraveKart Database Connected Successfully!");

        return connection;
    }
}