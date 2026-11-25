package com.cravekart.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.cravekart.dao.OrderDAO;
import com.cravekart.model.Order;
import com.cravekart.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    // 1. Place a new order
    @Override
    public int placeOrder(Order order) throws SQLException {

        String sql = "INSERT INTO Orders "
                + "(UserID, OrderDate, TotalAmount, Status, PaymentMethod, RestaurantID) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            ps.setDouble(3, order.getTotalAmount());
            ps.setString(4, order.getStatus());
            ps.setString(5, order.getPaymentMethod());
            ps.setInt(6, order.getRestaurantId());

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

    // 2. Get order using order ID
    @Override
    public Order getOrderById(int orderId) throws SQLException {

        String sql = "SELECT * FROM Orders WHERE OrderID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapOrder(rs);
                }
            }
        }

        return null;
    }

    // 3. Get orders placed by a particular user
    @Override
    public List<Order> getOrdersByUserId(int userId) throws SQLException {

        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM Orders WHERE UserID = ? "
                + "ORDER BY OrderDate DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orders.add(mapOrder(rs));
                }
            }
        }

        return orders;
    }

    // 4. Get all orders
    @Override
    public List<Order> getAllOrders() throws SQLException {

        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM Orders ORDER BY OrderDate DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                orders.add(mapOrder(rs));
            }
        }

        return orders;
    }

    // 5. Update order status
    @Override
    public boolean updateOrderStatus(int orderId, String status)
            throws SQLException {

        String sql = "UPDATE Orders SET Status = ? WHERE OrderID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);

            return ps.executeUpdate() > 0;
        }
    }

    // 6. Cancel order
    @Override
    public boolean cancelOrder(int orderId) throws SQLException {

        String sql = "UPDATE Orders SET Status = 'CANCELLED' "
                + "WHERE OrderID = ? AND Status NOT IN ('DELIVERED', 'CANCELLED')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            return ps.executeUpdate() > 0;
        }
    }

    // Convert ResultSet into Order object
    private Order mapOrder(ResultSet rs) throws SQLException {

        Order order = new Order();

        order.setOrderId(rs.getInt("OrderID"));
        order.setUserId(rs.getInt("UserID"));

        Timestamp timestamp = rs.getTimestamp("OrderDate");

        if (timestamp != null) {
            order.setOrderDate(timestamp.toLocalDateTime());
        }

        order.setTotalAmount(rs.getDouble("TotalAmount"));
        order.setStatus(rs.getString("Status"));
        order.setPaymentMethod(rs.getString("PaymentMethod"));
        order.setRestaurantId(rs.getInt("RestaurantID"));

        return order;
    }
}