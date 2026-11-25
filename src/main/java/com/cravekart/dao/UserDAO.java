package com.cravekart.dao;


import java.sql.SQLException;
import java.util.List;

import com.cravekart.model.User;

public interface UserDAO {

    // Register a new user
    int addUser(User user) throws SQLException;

    // Get user by ID
    User getUserById(int userId) throws SQLException;

    // Get user by email (login lookup)
    User getUserByEmail(String email) throws SQLException;

    // Get all users
    List<User> getAllUsers() throws SQLException;

    // Update user details
    boolean updateUser(User user) throws SQLException;

    // Delete user
    boolean deleteUser(int userId) throws SQLException;
}