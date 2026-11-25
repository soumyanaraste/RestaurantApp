package com.cravekart.dao;


import java.sql.SQLException;
import java.util.List;

import com.cravekart.model.Menu;

public interface MenuDAO {

    // Add food item
    int addMenuItem(Menu menu) throws SQLException;

    // Get food item by ID
    Menu getMenuById(int menuId) throws SQLException;

    // Get all menu items
    List<Menu> getAllMenuItems() throws SQLException;

    // Get menu by restaurant ID
    List<Menu> getMenuByRestaurantId(int restaurantId)
            throws SQLException;

    // Get available food items
    List<Menu> getAvailableMenuItems(int restaurantId)
            throws SQLException;

    // Update food item
    boolean updateMenuItem(Menu menu) throws SQLException;

    // Delete food item
    boolean deleteMenuItem(int menuId) throws SQLException;
}