package com.cravekart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cravekart.dao.RestaurantDAO;
import com.cravekart.model.Restaurant;
import com.cravekart.util.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {

    // 1. Add a new restaurant
    @Override
    public int addRestaurant(Restaurant restaurant) throws SQLException {

        String sql = "INSERT INTO Restaurant "
                + "(Name, CuisineType, DeliveryTime, Address, Rating, IsActive, ImagePath) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getCuisineType());
            ps.setInt(3, restaurant.getDeliveryTime());
            ps.setString(4, restaurant.getAddress());
            ps.setDouble(5, restaurant.getRating());
            ps.setBoolean(6, restaurant.isActive());
            ps.setString(7, restaurant.getImagePath());

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

    // 2. Get restaurant using ID
    @Override
    public Restaurant getRestaurantById(int restaurantId)
            throws SQLException {

        String sql = "SELECT * FROM Restaurant WHERE RestaurantID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRestaurant(rs);
                }
            }
        }

        return null;
    }

    // 3. Get all restaurants
    @Override
    public List<Restaurant> getAllRestaurants() throws SQLException {

        List<Restaurant> restaurants = new ArrayList<>();

        String sql = "SELECT * FROM Restaurant";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                restaurants.add(mapRestaurant(rs));
            }
        }

        return restaurants;
    }

    // 4. Get only active restaurants
    @Override
    public List<Restaurant> getActiveRestaurants() throws SQLException {

        List<Restaurant> restaurants = new ArrayList<>();

        String sql = "SELECT * FROM Restaurant WHERE IsActive = TRUE";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                restaurants.add(mapRestaurant(rs));
            }
        }

        return restaurants;
    }

    // 5. Update restaurant
    @Override
    public boolean updateRestaurant(Restaurant restaurant)
            throws SQLException {

        String sql = "UPDATE Restaurant SET Name = ?, CuisineType = ?, "
                + "DeliveryTime = ?, Address = ?, Rating = ?, "
                + "IsActive = ?, ImagePath = ? WHERE RestaurantID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getCuisineType());
            ps.setInt(3, restaurant.getDeliveryTime());
            ps.setString(4, restaurant.getAddress());
            ps.setDouble(5, restaurant.getRating());
            ps.setBoolean(6, restaurant.isActive());
            ps.setString(7, restaurant.getImagePath());
            ps.setInt(8, restaurant.getRestaurantId());

            return ps.executeUpdate() > 0;
        }
    }

    // 6. Delete restaurant
    @Override
    public boolean deleteRestaurant(int restaurantId)
            throws SQLException {

        String sql = "DELETE FROM Restaurant WHERE RestaurantID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            return ps.executeUpdate() > 0;
        }
    }

    // Convert ResultSet row into Restaurant object
    private Restaurant mapRestaurant(ResultSet rs)
            throws SQLException {

        Restaurant restaurant = new Restaurant();

        restaurant.setRestaurantId(rs.getInt("RestaurantID"));
        restaurant.setName(rs.getString("Name"));
        restaurant.setCuisineType(rs.getString("CuisineType"));
        restaurant.setDeliveryTime(rs.getInt("DeliveryTime"));
        restaurant.setAddress(rs.getString("Address"));
        restaurant.setRating(rs.getDouble("Rating"));
        restaurant.setActive(rs.getBoolean("IsActive"));
        restaurant.setImagePath(rs.getString("ImagePath"));

        return restaurant;
    }
}