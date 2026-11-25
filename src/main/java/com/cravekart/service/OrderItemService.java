package com.cravekart.service;

import java.sql.SQLException;
import java.util.List;

import com.cravekart.dao.OrderItemDAO;
import com.cravekart.dao.impl.OrderItemDAOImpl;
import com.cravekart.model.OrderItem;

public class OrderItemService {

    private OrderItemDAO orderItemDAO;

    // Constructor
    public OrderItemService() {
        this.orderItemDAO = new OrderItemDAOImpl();
    }

    // Add item to an order
    public int addOrderItem(OrderItem orderItem)
            throws SQLException {

        if (orderItem == null) {
            throw new IllegalArgumentException(
                "Order item cannot be null");
        }

        if (orderItem.getOrderId() <= 0) {
            throw new IllegalArgumentException(
                "Invalid Order ID");
        }

        if (orderItem.getMenuId() <= 0) {
            throw new IllegalArgumentException(
                "Invalid Menu ID");
        }

        if (orderItem.getQuantity() <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero");
        }

        if (orderItem.getUnitPrice() <= 0) {
            throw new IllegalArgumentException(
                "Unit price must be greater than zero");
        }

        // Calculate item total
        double itemTotal =
                orderItem.getQuantity() *
                orderItem.getUnitPrice();

        orderItem.setItemTotal(itemTotal);

        return orderItemDAO.addOrderItem(orderItem);
    }

    // Get order item by ID
    public OrderItem getOrderItemById(int orderItemId)
            throws SQLException {

        if (orderItemId <= 0) {
            throw new IllegalArgumentException(
                "Invalid Order Item ID");
        }

        return orderItemDAO.getOrderItemById(orderItemId);
    }

    // Get all items belonging to an order
    public List<OrderItem> getOrderItemsByOrderId(int orderId)
            throws SQLException {

        if (orderId <= 0) {
            throw new IllegalArgumentException(
                "Invalid Order ID");
        }

        return orderItemDAO.getOrderItemsByOrderId(orderId);
    }

    // Update order item
    public boolean updateOrderItem(OrderItem orderItem)
            throws SQLException {

        if (orderItem == null) {
            throw new IllegalArgumentException(
                "Order item cannot be null");
        }

        if (orderItem.getOrderItemId() <= 0) {
            throw new IllegalArgumentException(
                "Invalid Order Item ID");
        }

        if (orderItem.getOrderId() <= 0) {
            throw new IllegalArgumentException(
                "Invalid Order ID");
        }

        if (orderItem.getMenuId() <= 0) {
            throw new IllegalArgumentException(
                "Invalid Menu ID");
        }

        if (orderItem.getQuantity() <= 0) {
            throw new IllegalArgumentException(
                "Quantity must be greater than zero");
        }

        if (orderItem.getUnitPrice() <= 0) {
            throw new IllegalArgumentException(
                "Unit price must be greater than zero");
        }

        // Recalculate item total
        double itemTotal =
                orderItem.getQuantity() *
                orderItem.getUnitPrice();

        orderItem.setItemTotal(itemTotal);

        return orderItemDAO.updateOrderItem(orderItem);
    }

    // Delete individual order item
    public boolean deleteOrderItem(int orderItemId)
            throws SQLException {

        if (orderItemId <= 0) {
            throw new IllegalArgumentException(
                "Invalid Order Item ID");
        }

        return orderItemDAO.deleteOrderItem(orderItemId);
    }

    // Delete all items belonging to an order
    public boolean deleteItemsByOrderId(int orderId)
            throws SQLException {

        if (orderId <= 0) {
            throw new IllegalArgumentException(
                "Invalid Order ID");
        }

        return orderItemDAO.deleteItemsByOrderId(orderId);
    }
}