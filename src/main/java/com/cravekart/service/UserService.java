package com.cravekart.service;

import java.sql.SQLException;

import com.cravekart.dao.UserDAO;
import com.cravekart.dao.impl.UserDAOImpl;
import com.cravekart.model.User;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAOImpl();
    }

    // Register user
    public int registerUser(User user) throws SQLException {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (user.getUsername() == null ||
            user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }

        if (user.getEmail() == null ||
            user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (user.getPasswordHash() == null ||
            user.getPasswordHash().trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }

        User existingUser = userDAO.getUserByEmail(user.getEmail());

        if (existingUser != null) {
            throw new IllegalArgumentException("Email already registered!");
        }

        return userDAO.addUser(user);
    }

    // Get user by ID
    public User getUserById(int userId) throws SQLException {

        if (userId <= 0) {
            throw new IllegalArgumentException("Invalid User ID");
        }

        return userDAO.getUserById(userId);
    }

    // Get user by email
    public User getUserByEmail(String email) throws SQLException {

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        return userDAO.getUserByEmail(email);
    }
}