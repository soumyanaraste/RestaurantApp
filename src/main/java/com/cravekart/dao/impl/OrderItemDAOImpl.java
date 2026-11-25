package com.cravekart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cravekart.dao.OrderItemDAO;
import com.cravekart.model.OrderItem;
import com.cravekart.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    // 1. Add an item to an order
    @Override
    public int addOrderItem(OrderItem orderItem) throws SQLException {

        String sql = "INSERT INTO OrderItem "
                + "(OrderID, MenuID, Quantity, UnitPrice, ItemTotal) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, orderItem.getOrderId());
            ps.setInt(2, orderItem.getMenuId());
            ps.setInt(3, orderItem.getQuantity());
            ps.setDouble(4, orderItem.getUnitPrice());
            ps.setDouble(5, orderItem.getItemTotal());

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

    // 2. Get an order item using its ID
    @Override
    public OrderItem getOrderItemById(int orderItemId)
            throws SQLException {

        String sql = "SELECT * FROM OrderItem WHERE OrderItemID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderItemId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapOrderItem(rs);
                }
            }
        }

        return null;
    }

    // 3. Get all items belonging to an order
    @Override
    public List<OrderItem> getOrderItemsByOrderId(int orderId)
            throws SQLException {

        List<OrderItem> items = new ArrayList<>();

        String sql = "SELECT * FROM OrderItem WHERE OrderID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(mapOrderItem(rs));
                }
            }
        }

        return items;
    }

    // 4. Update an order item
    @Override
    public boolean updateOrderItem(OrderItem orderItem)
            throws SQLException {

        String sql = "UPDATE OrderItem SET Quantity = ?, "
                + "UnitPrice = ?, ItemTotal = ? "
                + "WHERE OrderItemID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderItem.getQuantity());
            ps.setDouble(2, orderItem.getUnitPrice());
            ps.setDouble(3, orderItem.getItemTotal());
            ps.setInt(4, orderItem.getOrderItemId());

            return ps.executeUpdate() > 0;
        }
    }

    // 5. Delete one order item
    @Override
    public boolean deleteOrderItem(int orderItemId)
            throws SQLException {

        String sql = "DELETE FROM OrderItem WHERE OrderItemID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderItemId);

            return ps.executeUpdate() > 0;
        }
    }

    // 6. Delete all items belonging to an order
    @Override
    public boolean deleteItemsByOrderId(int orderId)
            throws SQLException {

        String sql = "DELETE FROM OrderItem WHERE OrderID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            return ps.executeUpdate() > 0;
        }
    }

    // Convert ResultSet into OrderItem object
    private OrderItem mapOrderItem(ResultSet rs)
            throws SQLException {

        OrderItem item = new OrderItem();

        item.setOrderItemId(rs.getInt("OrderItemID"));
        item.setOrderId(rs.getInt("OrderID"));
        item.setMenuId(rs.getInt("MenuID"));
        item.setQuantity(rs.getInt("Quantity"));
        item.setUnitPrice(rs.getDouble("UnitPrice"));
        item.setItemTotal(rs.getDouble("ItemTotal"));

        return item;
    }
}