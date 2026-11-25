package com.cravekart.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.cravekart.util.DBConnection;

public class TestConnection {

    public static void main(String[] args) {

        System.out.println("==============================");
        System.out.println(" CRAVECRAFT DATABASE TEST");
        System.out.println("==============================");

        try (Connection con = DBConnection.getConnection()) {

            if (con != null && !con.isClosed()) {

                System.out.println("Connection Successful!");

                System.out.println(
                    "Database Name: " + con.getCatalog()
                );

                System.out.println("Status: ACTIVE");

            } else {

                System.out.println("Connection Failed!");
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        System.out.println("==============================");
    }
}