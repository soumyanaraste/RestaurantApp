package com.cravekart.test;

import java.sql.SQLException;
import java.util.List;

import com.cravekart.model.Order;
import com.cravekart.service.OrderService;
import java.time.LocalDateTime;

public class OrderServiceTest {

    public static void main(String[] args) {

        OrderService orderService = new OrderService();

        try {

            // 1. Get all orders
            System.out.println("===== ALL ORDERS =====");

            List<Order> orders = orderService.getAllOrders();

            for (Order order : orders) {
                displayOrder(order);
            }

            // 2. Get order by ID
            System.out.println("\n===== ORDER BY ID =====");

            Order order = orderService.getOrderById(1);

            if (order != null) {
                displayOrder(order);
            } else {
                System.out.println("Order not found");
            }

            // 3. Get orders by user ID
            System.out.println("\n===== ORDERS BY USER ID =====");

            List<Order> userOrders =
                    orderService.getOrdersByUserId(1);

            for (Order item : userOrders) {
                displayOrder(item);
            }
         // 4. Place a new order
            System.out.println("\n===== PLACE NEW ORDER =====");

            Order newOrder = new Order();

            newOrder.setUserId(1);
            newOrder.setRestaurantId(1);
            newOrder.setTotalAmount(500.0);
            newOrder.setPaymentMethod("Cash");
            newOrder.setStatus("Placed");
            newOrder.setOrderDate(LocalDateTime.now());

            int result = orderService.placeOrder(newOrder);

            if (result > 0) {
                System.out.println("Order placed successfully!");
                System.out.println("Generated Order ID: " + result);

                // Verify inserted order
                Order insertedOrder = orderService.getOrderById(result);

                if (insertedOrder != null) {
                    System.out.println("\n===== INSERTED ORDER DETAILS =====");
                    displayOrder(insertedOrder);
                }
            } else {
                System.out.println("Order placement failed.");
            }
            
         // 5. Update order status
            System.out.println("\n===== UPDATE ORDER STATUS =====");

            boolean updated = orderService.updateOrderStatus(
                result, "CONFIRMED"
            );

            if (updated) {
                System.out.println("Order status updated successfully!");

                // Verify updated status
                Order updatedOrder = orderService.getOrderById(result);

                if (updatedOrder != null) {
                    System.out.println("Updated Status: "
                            + updatedOrder.getStatus());
                }

            } else {
                System.out.println("Order status update failed.");
            }
            
         // 6. Cancel order
            System.out.println("\n===== CANCEL ORDER =====");

            boolean cancelled = orderService.cancelOrder(result);

            if (cancelled) {
                System.out.println("Order cancelled successfully!");

                // Verify cancellation
                Order cancelledOrder = orderService.getOrderById(result);

                if (cancelledOrder != null) {
                    System.out.println("Current Order Status: "
                            + cancelledOrder.getStatus());
                }

            } else {
                System.out.println("Order cancellation failed.");
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (IllegalArgumentException e) {

            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    // Display order details
    public static void displayOrder(Order order) {

        System.out.println("Order ID: " + order.getOrderId());

        System.out.println("User ID: " + order.getUserId());

        System.out.println("Restaurant ID: " +
                order.getRestaurantId());

        System.out.println("Order Date: " +
                order.getOrderDate());

        System.out.println("Total Amount: " +
                order.getTotalAmount());

        System.out.println("Status: " +
                order.getStatus());

        System.out.println("Payment Method: " +
                order.getPaymentMethod());

        System.out.println("--------------------------");
    }
}