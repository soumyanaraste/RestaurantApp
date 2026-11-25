package com.cravekart.dao;


import java.sql.SQLException;
import java.util.List;

import com.cravekart.model.OrderItem;

public interface OrderItemDAO {

    // Add item to an order
    int addOrderItem(OrderItem orderItem)
            throws SQLException;

    // Get order item by ID
    OrderItem getOrderItemById(int orderItemId)
            throws SQLException;

    // Get all items belonging to an order
    List<OrderItem> getOrderItemsByOrderId(int orderId)
            throws SQLException;

    // Update order item
    boolean updateOrderItem(OrderItem orderItem)
            throws SQLException;

    // Delete individual order item
    boolean deleteOrderItem(int orderItemId)
            throws SQLException;

    // Delete all items belonging to an order
    boolean deleteItemsByOrderId(int orderId)
            throws SQLException;
}