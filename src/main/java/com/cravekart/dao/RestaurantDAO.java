package com.cravekart.dao;



import java.sql.SQLException;
import java.util.List;

import com.cravekart.model.Restaurant;

public interface RestaurantDAO {

    // Add a new restaurant
    int addRestaurant(Restaurant restaurant)
            throws SQLException;

    // Get restaurant by ID
    Restaurant getRestaurantById(int restaurantId)
            throws SQLException;

    // Get all restaurants
    List<Restaurant> getAllRestaurants()
            throws SQLException;

    // Get active restaurants
    List<Restaurant> getActiveRestaurants()
            throws SQLException;

    // Update restaurant details
    boolean updateRestaurant(Restaurant restaurant)
            throws SQLException;

    // Delete restaurant
    boolean deleteRestaurant(int restaurantId)
            throws SQLException;
}