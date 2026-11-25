package com.cravekart.service;

import java.sql.SQLException;
import java.util.List;

import com.cravekart.dao.RestaurantDAO;
import com.cravekart.dao.impl.RestaurantDAOImpl;
import com.cravekart.model.Restaurant;

public class RestaurantService {

    private RestaurantDAO restaurantDAO;

    // Constructor
    public RestaurantService() {
        restaurantDAO = new RestaurantDAOImpl();
    }

    // Add Restaurant
    public int addRestaurant(Restaurant restaurant)
            throws SQLException {

        if (restaurant == null) {
            throw new IllegalArgumentException(
                "Restaurant cannot be null"
            );
        }

        return restaurantDAO.addRestaurant(restaurant);
    }

    // Get Restaurant by ID
    public Restaurant getRestaurantById(int restaurantId)
            throws SQLException {

        if (restaurantId <= 0) {
            throw new IllegalArgumentException(
                "Invalid Restaurant ID"
            );
        }

        return restaurantDAO.getRestaurantById(restaurantId);
    }

    // Get All Restaurants
    public List<Restaurant> getAllRestaurants()
            throws SQLException {

        return restaurantDAO.getAllRestaurants();
    }

    // Get Active Restaurants
    public List<Restaurant> getActiveRestaurants()
            throws SQLException {

        return restaurantDAO.getActiveRestaurants();
    }

    // Update Restaurant
    public boolean updateRestaurant(Restaurant restaurant)
            throws SQLException {

        if (restaurant == null) {
            throw new IllegalArgumentException(
                "Restaurant cannot be null"
            );
        }

        if (restaurant.getRestaurantId() <= 0) {
            throw new IllegalArgumentException(
                "Invalid Restaurant ID"
            );
        }

        return restaurantDAO.updateRestaurant(restaurant);
    }

    // Delete Restaurant
    public boolean deleteRestaurant(int restaurantId)
            throws SQLException {

        if (restaurantId <= 0) {
            throw new IllegalArgumentException(
                "Invalid Restaurant ID"
            );
        }

        return restaurantDAO.deleteRestaurant(restaurantId);
    }
}