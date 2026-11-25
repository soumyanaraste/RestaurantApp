package com.cravekart.dao;



import java.sql.SQLException;
import java.util.List;

import com.cravekart.model.Order;

public interface OrderDAO {

    // Place a new order
    int placeOrder(Order order) throws SQLException;

    // Get order by ID
    Order getOrderById(int orderId) throws SQLException;

    // Get orders placed by a particular user
    List<Order> getOrdersByUserId(int userId)
            throws SQLException;

    // Get all orders
    List<Order> getAllOrders() throws SQLException;

    // Update order status
    boolean updateOrderStatus(int orderId, String status)
            throws SQLException;

    // Cancel an order
    boolean cancelOrder(int orderId) throws SQLException;
}