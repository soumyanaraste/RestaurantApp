package com.cravekart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cravekart.dao.MenuDAO;
import com.cravekart.model.Menu;
import com.cravekart.util.DBConnection;

public class MenuDAOImpl implements MenuDAO {

    // 1. Add a new menu item
    @Override
    public int addMenuItem(Menu menu) throws SQLException {

        String sql = "INSERT INTO Menu "
                + "(RestaurantID, ItemName, Description, Price, IsAvailable, ImagePath) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, menu.getRestaurantId());
            ps.setString(2, menu.getItemName());
            ps.setString(3, menu.getDescription());
            ps.setDouble(4, menu.getPrice());
            ps.setBoolean(5, menu.isAvailable());
            ps.setString(6, menu.getImagePath());

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

    // 2. Get menu item by ID
    @Override
    public Menu getMenuById(int menuId) throws SQLException {

        String sql = "SELECT * FROM Menu WHERE MenuID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, menuId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapMenu(rs);
                }
            }
        }

        return null;
    }

    // 3. Get all menu items
    @Override
    public List<Menu> getAllMenuItems() throws SQLException {

        List<Menu> menus = new ArrayList<>();

        String sql = "SELECT * FROM Menu";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                menus.add(mapMenu(rs));
            }
        }

        return menus;
    }

    // 4. Get menu items belonging to one restaurant
    @Override
    public List<Menu> getMenuByRestaurantId(int restaurantId)
            throws SQLException {

        List<Menu> menus = new ArrayList<>();

        String sql = "SELECT * FROM Menu WHERE RestaurantID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    menus.add(mapMenu(rs));
                }
            }
        }

        return menus;
    }

    // 5. Get available menu items of a restaurant
    @Override
    public List<Menu> getAvailableMenuItems(int restaurantId)
            throws SQLException {

        List<Menu> menus = new ArrayList<>();

        String sql = "SELECT * FROM Menu "
                + "WHERE RestaurantID = ? AND IsAvailable = TRUE";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    menus.add(mapMenu(rs));
                }
            }
        }

        return menus;
    }

    // 6. Update menu item
    @Override
    public boolean updateMenuItem(Menu menu) throws SQLException {

        String sql = "UPDATE Menu SET RestaurantID = ?, ItemName = ?, "
                + "Description = ?, Price = ?, IsAvailable = ?, "
                + "ImagePath = ? WHERE MenuID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, menu.getRestaurantId());
            ps.setString(2, menu.getItemName());
            ps.setString(3, menu.getDescription());
            ps.setDouble(4, menu.getPrice());
            ps.setBoolean(5, menu.isAvailable());
            ps.setString(6, menu.getImagePath());
            ps.setInt(7, menu.getMenuId());

            return ps.executeUpdate() > 0;
        }
    }

    // 7. Delete menu item
    @Override
    public boolean deleteMenuItem(int menuId) throws SQLException {

        String sql = "DELETE FROM Menu WHERE MenuID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, menuId);

            return ps.executeUpdate() > 0;
        }
    }

    // Convert ResultSet into Menu object
    private Menu mapMenu(ResultSet rs) throws SQLException {

        Menu menu = new Menu();

        menu.setMenuId(rs.getInt("MenuID"));
        menu.setRestaurantId(rs.getInt("RestaurantID"));
        menu.setItemName(rs.getString("ItemName"));
        menu.setDescription(rs.getString("Description"));
        menu.setPrice(rs.getDouble("Price"));
        menu.setAvailable(rs.getBoolean("IsAvailable"));
        menu.setImagePath(rs.getString("ImagePath"));

        return menu;
    }
}