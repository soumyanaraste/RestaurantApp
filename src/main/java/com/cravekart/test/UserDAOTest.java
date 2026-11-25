package com.cravekart.test;

import java.sql.SQLException;

import com.cravekart.dao.UserDAO;
import com.cravekart.dao.impl.UserDAOImpl;
import com.cravekart.model.User;

public class UserDAOTest {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAOImpl();

        try {

            // Create a new User object
            User user = new User();

            user.setUsername("CraveKartUser");
            user.setEmail("test@cravekart.com");
            user.setAddress("Bangalore");
            user.setRole("CUSTOMER");

            // Use a real password hash in the actual application.
            // This placeholder is only for testing DAO insertion.
            user.setPasswordHash("TEST_HASH_PLACEHOLDER");

            // Insert user into database
            int userId = userDAO.addUser(user);

            if (userId > 0) {

                System.out.println("User Registered Successfully!");
                System.out.println("Generated User ID: " + userId);

                // Retrieve user using generated ID
                User savedUser = userDAO.getUserById(userId);

                if (savedUser != null) {

                    System.out.println("User Details:");

                    System.out.println(
                        "Username: " + savedUser.getUsername()
                    );

                    System.out.println(
                        "Email: " + savedUser.getEmail()
                    );

                    System.out.println(
                        "Address: " + savedUser.getAddress()
                    );
                }

            } else {

                System.out.println("User Registration Failed!");
            }

        } catch (SQLException e) {

            System.out.println("Database Operation Failed!");
            e.printStackTrace();
        }
    }
}