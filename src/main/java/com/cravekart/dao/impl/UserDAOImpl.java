package com.cravekart.dao.impl;



import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cravekart.dao.UserDAO;
import com.cravekart.model.User;
import com.cravekart.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    // 1. Register a new user
	@Override
	public int addUser(User user) throws SQLException {

	    String sql = "INSERT INTO Users "
	            + "(Username, PasswordHash, Email, Address, Role) "
	            + "VALUES (?, ?, ?, ?, ?)";

	    try (Connection con = DBConnection.getConnection();

	         PreparedStatement ps = con.prepareStatement(
	                 sql,
	                 Statement.RETURN_GENERATED_KEYS)) {

	        ps.setString(1, user.getUsername());
	        ps.setString(2, user.getPasswordHash());
	        ps.setString(3, user.getEmail());
	        ps.setString(4, user.getAddress());
	        ps.setString(5, user.getRole());

	        int rows = ps.executeUpdate();

	        if (rows > 0) {

	            try (ResultSet rs = ps.getGeneratedKeys()) {

	                if (rs.next()) {
	                    return rs.getInt(1);
	                }
	            }
	        }
	    }

	    return 0;
	}

    // 2. Get user by ID
    @Override
    public User getUserById(int userId) throws SQLException {

        String sql = "SELECT * FROM Users WHERE UserID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapUser(rs);
                }
            }
        }

        return null;
    }

    // 3. Get user by email
    @Override
    public User getUserByEmail(String email) throws SQLException {

        String sql = "SELECT * FROM Users WHERE Email = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapUser(rs);
                }
            }
        }

        return null;
    }

    // 4. Get all users
    @Override
    public List<User> getAllUsers() throws SQLException {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM Users";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(mapUser(rs));
            }
        }

        return users;
    }

    // 5. Update user details
    @Override
    public boolean updateUser(User user) throws SQLException {

        String sql = "UPDATE Users SET Username = ?, "
                + "Email = ?, Address = ? WHERE UserID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getAddress());
            ps.setInt(4, user.getUserId());

            return ps.executeUpdate() > 0;
        }
    }

    // 6. Delete user
    @Override
    public boolean deleteUser(int userId) throws SQLException {

        String sql = "DELETE FROM Users WHERE UserID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            return ps.executeUpdate() > 0;
        }
    }

    // Helper method: Convert ResultSet into User object
    private User mapUser(ResultSet rs) throws SQLException {

        User user = new User();

        user.setUserId(rs.getInt("UserID"));
        user.setUsername(rs.getString("Username"));
        user.setPasswordHash(rs.getString("PasswordHash"));
        user.setEmail(rs.getString("Email"));
        user.setAddress(rs.getString("Address"));
        user.setRole(rs.getString("Role"));

        if (rs.getTimestamp("CreatedDate") != null) {
            user.setCreatedDate(
                rs.getTimestamp("CreatedDate").toLocalDateTime()
            );
        }

        if (rs.getTimestamp("LastLoginDate") != null) {
            user.setLastLoginDate(
                rs.getTimestamp("LastLoginDate").toLocalDateTime()
            );
        }

        return user;
    }
}