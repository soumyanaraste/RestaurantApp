package com.cravekart.service;

import java.sql.SQLException;
import java.util.List;

import com.cravekart.dao.OrderDAO;
import com.cravekart.dao.impl.OrderDAOImpl;
import com.cravekart.model.Order;

public class OrderService {

    private OrderDAO orderDAO;

    // Constructor
    public OrderService() {
        this.orderDAO = new OrderDAOImpl();
    }

    // Place a new order
    public int placeOrder(Order order) throws SQLException {

        if (order == null) {
            throw new IllegalArgumentException(
                "Order cannot be null");
        }

        if (order.getUserId() <= 0) {
            throw new IllegalArgumentException(
                "Invalid User ID");
        }

        if (order.getRestaurantId() <= 0) {
            throw new IllegalArgumentException(
                "Invalid Restaurant ID");
        }

        if (order.getTotalAmount() <= 0) {
            throw new IllegalArgumentException(
                "Order amount must be greater than zero");
        }

        if (order.getPaymentMethod() == null ||
            order.getPaymentMethod().trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Payment method is required");
        }

        return orderDAO.placeOrder(order);
    }

    // Get order by ID
    public Order getOrderById(int orderId) throws SQLException {

        if (orderId <= 0) {
            throw new IllegalArgumentException(
                "Invalid Order ID");
        }

        return orderDAO.getOrderById(orderId);
    }

    // Get all orders
    public List<Order> getAllOrders() throws SQLException {

        return orderDAO.getAllOrders();
    }

    // Get orders by user ID
    public List<Order> getOrdersByUserId(int userId)
            throws SQLException {

        if (userId <= 0) {
            throw new IllegalArgumentException(
                "Invalid User ID");
        }

        return orderDAO.getOrdersByUserId(userId);
    }

    // Update order status
    public boolean updateOrderStatus(int orderId, String status)
            throws SQLException {

        if (orderId <= 0) {
            throw new IllegalArgumentException(
                "Invalid Order ID");
        }

        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Order status cannot be empty");
        }

        return orderDAO.updateOrderStatus(orderId, status);
    }

    // Cancel an order
    public boolean cancelOrder(int orderId) throws SQLException {

        if (orderId <= 0) {
            throw new IllegalArgumentException(
                "Invalid Order ID");
        }

        return orderDAO.cancelOrder(orderId);
    }
}