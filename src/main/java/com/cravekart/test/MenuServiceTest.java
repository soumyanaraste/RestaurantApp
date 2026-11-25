package com.cravekart.test;

import java.sql.SQLException;
import java.util.List;

import com.cravekart.model.Menu;
import com.cravekart.service.MenuService;

public class MenuServiceTest {

    public static void main(String[] args) {

        MenuService menuService = new MenuService();

        try {

            // 1. Get all menu items
            System.out.println("===== ALL MENU ITEMS =====");

            List<Menu> menus = menuService.getAllMenuItems();

            for (Menu menu : menus) {
                displayMenu(menu);
            }

            // 2. Get menu item by ID
            System.out.println("\n===== MENU BY ID =====");

            Menu menu = menuService.getMenuById(1);

            if (menu != null) {
                displayMenu(menu);
            } else {
                System.out.println("Menu item not found");
            }

            // 3. Get menu by restaurant ID
            System.out.println("\n===== MENU BY RESTAURANT ID =====");

            List<Menu> restaurantMenus =
                    menuService.getMenuByRestaurantId(1);

            for (Menu item : restaurantMenus) {
                displayMenu(item);
            }

            // 4. Get available menu items
            System.out.println("\n===== AVAILABLE MENU ITEMS =====");

            List<Menu> availableMenus =
                    menuService.getAvailableMenuItems(1);

            for (Menu item : availableMenus) {
                displayMenu(item);
            }
            
         // 5. Insert new menu item
            System.out.println("\n===== INSERT MENU ITEM =====");

            Menu newMenu = new Menu();

            newMenu.setRestaurantId(1);
            newMenu.setItemName("Paneer Tikka Pizza");
            newMenu.setDescription("Delicious paneer pizza with extra cheese");
            newMenu.setPrice(250.0);
            newMenu.setAvailable(true);
            newMenu.setImagePath("images/paneer-pizza.jpg");

            int result = menuService.addMenuItem(newMenu);

            if (result > 0) {

                System.out.println("Menu item inserted successfully!");
                System.out.println("Generated Menu ID: " + result);

            } else {

                System.out.println("Menu item insertion failed.");
            }
            
         // 6. Update menu item
            System.out.println("\n===== UPDATE MENU ITEM =====");

            Menu updateMenu = menuService.getMenuById(result);

            if (updateMenu != null) {

                updateMenu.setItemName("Paneer Tikka Pizza Updated");
                updateMenu.setDescription("Updated paneer pizza with extra cheese");
                updateMenu.setPrice(280.0);
                updateMenu.setAvailable(true);

                boolean updated = menuService.updateMenuItem(updateMenu);

                if (updated) {
                    System.out.println("Menu item updated successfully!");
                } else {
                    System.out.println("Menu item update failed.");
                }

            } else {
                System.out.println("Menu item not found.");
            }
            
            
         // 7. Delete menu item
            System.out.println("\n===== DELETE MENU ITEM =====");

            boolean deleted = menuService.deleteMenuItem(result);

            if (deleted) {
                System.out.println("Menu item deleted successfully!");
            } else {
                System.out.println("Menu item deletion failed.");
            }

            // Verify deletion
            Menu deletedMenu = menuService.getMenuById(result);

            if (deletedMenu == null) {
                System.out.println("Delete verification successful!");
            } else {
                System.out.println("Menu item still exists.");
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (IllegalArgumentException e) {

            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    // Display menu details
    public static void displayMenu(Menu menu) {

        System.out.println("Menu ID: " + menu.getMenuId());
        System.out.println("Restaurant ID: " + menu.getRestaurantId());
        System.out.println("Item Name: " + menu.getItemName());
        System.out.println("Description: " + menu.getDescription());
        System.out.println("Price: " + menu.getPrice());
        System.out.println("Available: " + menu.isAvailable());
        System.out.println("Image Path: " + menu.getImagePath());

        System.out.println("--------------------------");
    }
}