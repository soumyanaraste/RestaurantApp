package com.cravekart.test;

import java.sql.SQLException;

import com.cravekart.model.User;
import com.cravekart.service.UserService;

public class UserServiceTest {

    public static void main(String[] args) {

        try {

            UserService service = new UserService();

            // Test 1: Get user by ID
            System.out.println("\n===== GET USER BY ID =====");

            User savedUser = service.getUserById(1);

            if (savedUser != null) {
                System.out.println("User found successfully!");
                System.out.println("User ID: " + savedUser.getUserId());
                System.out.println("Username: " + savedUser.getUsername());
                System.out.println("Email: " + savedUser.getEmail());
                System.out.println("Address: " + savedUser.getAddress());
                System.out.println("Role: " + savedUser.getRole());
            } else {
                System.out.println("User not found.");
            }

            // Test 2: Get user by email
            System.out.println("\n===== GET USER BY EMAIL =====");

            User emailUser = service.getUserByEmail(
                    "testcustomer@gmail.com");

            if (emailUser != null) {
                System.out.println("User found successfully!");
                System.out.println("User ID: " + emailUser.getUserId());
                System.out.println("Username: " + emailUser.getUsername());
                System.out.println("Email: " + emailUser.getEmail());
                System.out.println("Address: " + emailUser.getAddress());
                System.out.println("Role: " + emailUser.getRole());
            } else {
                System.out.println("User not found.");
            }
         // Test 3: Duplicate email validation
            System.out.println("\n===== DUPLICATE EMAIL VALIDATION =====");

            try {

                User duplicateUser = new User();

                duplicateUser.setUsername("AnotherCustomer");
                duplicateUser.setPasswordHash("test_hash_456");
                duplicateUser.setEmail("testcustomer@gmail.com");
                duplicateUser.setAddress("Bangalore");
                duplicateUser.setRole("CUSTOMER");

                service.registerUser(duplicateUser);

                System.out.println("Duplicate registration was allowed!");

            } catch (IllegalArgumentException e) {

                System.out.println("Duplicate email validation successful!");
                System.out.println("Message: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }
}