package com.cravekart.test;



import com.cravekart.model.User;

public class ModelTest {

    public static void main(String[] args) {

        User user = new User();

        user.setUserId(1);
        user.setUsername("Srushti");
        user.setEmail("srushti@gmail.com");
        user.setAddress("Bangalore");
        user.setRole("CUSTOMER");

        System.out.println("CRAVECRAFT USER DETAILS");

        System.out.println("User ID: " + user.getUserId());

        System.out.println("Username: " + user.getUsername());

        System.out.println("Email: " + user.getEmail());

        System.out.println("Address: " + user.getAddress());

        System.out.println("Role: " + user.getRole());
    }
}