package com.cravekart.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.cravekart.dao.impl.OrderDAOImpl;
import com.cravekart.model.Order;

public class OrderDAO {

    public static void main(String[] args) {

        try {
            OrderDAOImpl dao = new OrderDAOImpl();

            Order order = new Order();

            // These IDs must already exist in your database
            order.setUserId(1);
            order.setRestaurantId(1);

            order.setOrderDate(LocalDateTime.now());
            order.setTotalAmount(new Double("350.00"));
            order.setStatus("PLACED");
            order.setPaymentMethod("CASH");

            int orderId = dao.placeOrder(order);

            System.out.println("Order placed successfully!");
            System.out.println("Order ID: " + orderId);

            Order savedOrder = dao.getOrderById(orderId);

            if (savedOrder != null) {
                System.out.println("User ID: " + savedOrder.getUserId());
                System.out.println("Total: " + savedOrder.getTotalAmount());
                System.out.println("Status: " + savedOrder.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}