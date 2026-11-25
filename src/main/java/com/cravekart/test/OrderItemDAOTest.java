package com.cravekart.test;



import com.cravekart.dao.impl.OrderItemDAOImpl;
import com.cravekart.model.OrderItem;

public class OrderItemDAOTest {

    public static void main(String[] args) {

        try {
            OrderItemDAOImpl dao = new OrderItemDAOImpl();

            OrderItem item = new OrderItem();

            // Use existing IDs from your database
            item.setOrderId(1);
            item.setMenuId(1);
            item.setQuantity(2);

            item.setUnitPrice(new Double("120.00"));
            item.setItemTotal(new Double("240.00"));

            int itemId = dao.addOrderItem(item);

            System.out.println("Order item added successfully!");
            System.out.println("Order Item ID: " + itemId);

            OrderItem savedItem = dao.getOrderItemById(itemId);

            if (savedItem != null) {
                System.out.println("Order ID: " + savedItem.getOrderId());
                System.out.println("Menu ID: " + savedItem.getMenuId());
                System.out.println("Quantity: " + savedItem.getQuantity());
                System.out.println("Item Total: " + savedItem.getItemTotal());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}