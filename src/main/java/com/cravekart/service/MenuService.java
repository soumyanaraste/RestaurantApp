package com.cravekart.service;

import java.sql.SQLException;
import java.util.List;

import com.cravekart.dao.MenuDAO;
import com.cravekart.dao.impl.MenuDAOImpl;
import com.cravekart.model.Menu;

public class MenuService {

    private MenuDAO menuDAO;

    // Constructor
    public MenuService() {
        this.menuDAO = new MenuDAOImpl();
    }

    // Add menu item
    public int addMenuItem(Menu menu) throws SQLException {

        if (menu == null) {
            throw new IllegalArgumentException("Menu cannot be null");
        }

        if (menu.getItemName() == null ||
            menu.getItemName().trim().isEmpty()) {
            throw new IllegalArgumentException("Item name is required");
        }

        if (menu.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }

        return menuDAO.addMenuItem(menu);
    }

    // Get menu item by ID
    public Menu getMenuById(int menuId) throws SQLException {

        if (menuId <= 0) {
            throw new IllegalArgumentException("Invalid Menu ID");
        }

        return menuDAO.getMenuById(menuId);
    }

    // Get all menu items
    public List<Menu> getAllMenuItems() throws SQLException {

        return menuDAO.getAllMenuItems();
    }

    // Get menu items by restaurant ID
    public List<Menu> getMenuByRestaurantId(int restaurantId)
            throws SQLException {

        if (restaurantId <= 0) {
            throw new IllegalArgumentException("Invalid Restaurant ID");
        }

        return menuDAO.getMenuByRestaurantId(restaurantId);
    }

    // Get available menu items
    public List<Menu> getAvailableMenuItems(int restaurantId)
            throws SQLException {

        if (restaurantId <= 0) {
            throw new IllegalArgumentException("Invalid Restaurant ID");
        }

        return menuDAO.getAvailableMenuItems(restaurantId);
    }

    // Update menu item
    public boolean updateMenuItem(Menu menu) throws SQLException {

        if (menu == null) {
            throw new IllegalArgumentException("Menu cannot be null");
        }

        if (menu.getMenuId() <= 0) {
            throw new IllegalArgumentException("Invalid Menu ID");
        }

        if (menu.getItemName() == null ||
            menu.getItemName().trim().isEmpty()) {
            throw new IllegalArgumentException("Item name is required");
        }

        if (menu.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }

        return menuDAO.updateMenuItem(menu);
    }

    // Delete menu item
    public boolean deleteMenuItem(int menuId) throws SQLException {

        if (menuId <= 0) {
            throw new IllegalArgumentException("Invalid Menu ID");
        }

        return menuDAO.deleteMenuItem(menuId);
    }
}