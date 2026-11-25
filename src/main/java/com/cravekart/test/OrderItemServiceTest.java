package com.cravekart.test;

import java.sql.SQLException;
import java.util.List;

import com.cravekart.model.OrderItem;
import com.cravekart.service.OrderItemService;

public class OrderItemServiceTest {

    public static void main(String[] args) {

        OrderItemService orderItemService =
                new OrderItemService();

        try {

            // 1. Get all items belonging to an order
            System.out.println("===== ORDER ITEMS BY ORDER ID =====");

            List<OrderItem> items =
                    orderItemService.getOrderItemsByOrderId(1);

            if (items != null && !items.isEmpty()) {

                for (OrderItem item : items) {
                    displayOrderItem(item);
                }

            } else {
                System.out.println("No order items found.");
            }


            // 2. Get order item by ID
            System.out.println("\n===== ORDER ITEM BY ID =====");

            OrderItem orderItem =
                    orderItemService.getOrderItemById(4);

            if (orderItem != null) {
                displayOrderItem(orderItem);
            } else {
                System.out.println("Order item not found.");
            }


            // 3. Insert new order item
            System.out.println("\n===== INSERT ORDER ITEM =====");

            OrderItem newItem = new OrderItem();

            newItem.setOrderId(1);
            newItem.setMenuId(1);
            newItem.setQuantity(3);
            newItem.setUnitPrice(120.0);

            int result =
                    orderItemService.addOrderItem(newItem);

            if (result > 0) {

                System.out.println(
                        "Order item inserted successfully!");

                System.out.println(
                        "Generated Order Item ID: " + result);

                System.out.println(
                        "Item Total: " + newItem.getItemTotal());

                // Verify inserted item
                OrderItem insertedItem =
                        orderItemService.getOrderItemById(result);

                if (insertedItem != null) {

                    System.out.println(
                            "\n===== INSERTED ITEM DETAILS =====");

                    displayOrderItem(insertedItem);
                }

            } else {
                System.out.println(
                        "Order item insertion failed.");
            }


            
         // 4. Update order item
            System.out.println("\n===== UPDATE ORDER ITEM =====");

            OrderItem updateItem = new OrderItem();

            updateItem.setOrderItemId(5);
            updateItem.setOrderId(1);
            updateItem.setMenuId(1);
            updateItem.setQuantity(5);
            updateItem.setUnitPrice(150.0);

            boolean updated =
                    orderItemService.updateOrderItem(updateItem);

            if (updated) {
                System.out.println("Order item updated successfully!");
                System.out.println("New Quantity: " +
                        updateItem.getQuantity());
                System.out.println("New Unit Price: " +
                        updateItem.getUnitPrice());
                System.out.println("New Item Total: " +
                        updateItem.getItemTotal());
            } else {
                System.out.println("Order item update failed.");
            }
            
         // 5. Delete order item
            System.out.println("\n===== DELETE ORDER ITEM =====");

            int orderItemId = 5;

            boolean deleted =
                    orderItemService.deleteOrderItem(orderItemId);

            if (deleted) {
                System.out.println("Order item deleted successfully!");
            } else {
                System.out.println("Order item deletion failed.");
            }

            // Verify deletion
            OrderItem deletedItem =
                    orderItemService.getOrderItemById(orderItemId);

            if (deletedItem == null) {
                System.out.println("Delete verification successful!");
                System.out.println("Order item not found in database.");
            } else {
                System.out.println("Order item still exists.");
            }
         // 6. Delete all items belonging to an order
            System.out.println("\n===== DELETE ITEMS BY ORDER ID =====");

            int orderId = 1;

            boolean deleted1 =
                    orderItemService.deleteItemsByOrderId(orderId);

            if (deleted1) {
                System.out.println(
                        "All order items deleted successfully!");
            } else {
                System.out.println(
                        "No order items deleted.");
            }

            // Verify deletion
            List<OrderItem> remainingItems =
                    orderItemService.getOrderItemsByOrderId(orderId);

            if (remainingItems == null || remainingItems.isEmpty()) {
                System.out.println(
                        "Delete verification successful!");
            } else {
                System.out.println(
                        "Some order items still exist.");
            }
        } 
        
        
        
        
        catch (SQLException e) {

            System.out.println("Database Error:");
            e.printStackTrace();

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Validation Error: " + e.getMessage());
        }
    }


    // Display order item details
    public static void displayOrderItem(OrderItem item) {

        System.out.println("Order Item ID: " +
                item.getOrderItemId());

        System.out.println("Order ID: " +
                item.getOrderId());

        System.out.println("Menu ID: " +
                item.getMenuId());

        System.out.println("Quantity: " +
                item.getQuantity());

        System.out.println("Unit Price: " +
                item.getUnitPrice());

        System.out.println("Item Total: " +
                item.getItemTotal());

        System.out.println("--------------------------");
    }
}