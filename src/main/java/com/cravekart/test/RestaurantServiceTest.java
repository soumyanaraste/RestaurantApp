package com.cravekart.test;

import java.sql.SQLException;
import java.util.List;

import com.cravekart.model.Restaurant;
import com.cravekart.service.RestaurantService;

public class RestaurantServiceTest {

    public static void main(String[] args) {

        try {

            RestaurantService service = new RestaurantService();

            // 1. Add Restaurant
            Restaurant restaurant = new Restaurant();

            restaurant.setName("Food Palace");
            restaurant.setCuisineType("Indian");
            restaurant.setDeliveryTime(30);
            restaurant.setAddress("Bangalore");
            restaurant.setRating(4.5);
            restaurant.setActive(true);
            restaurant.setImagePath("foodpalace.jpg");

            int restaurantId = service.addRestaurant(restaurant);

            System.out.println("Restaurant Added Successfully!");
            System.out.println("Restaurant ID: " + restaurantId);


            // 2. Get Restaurant by ID
            Restaurant savedRestaurant =
                    service.getRestaurantById(restaurantId);

            if (savedRestaurant != null) {

                System.out.println("\nRestaurant Details:");
                System.out.println("Name: " + savedRestaurant.getName());
                System.out.println("Cuisine: " + savedRestaurant.getCuisineType());
                System.out.println("Address: " + savedRestaurant.getAddress());
                System.out.println("Rating: " + savedRestaurant.getRating());
            }


            // 3. Get All Restaurants
            List<Restaurant> restaurants =
                    service.getAllRestaurants();

            System.out.println("\nAll Restaurants:");

            for (Restaurant r : restaurants) {
                System.out.println(
                    r.getRestaurantId() + " - " + r.getName()
                );
            }


            // 4. Get Active Restaurants
            List<Restaurant> activeRestaurants =
                    service.getActiveRestaurants();

            System.out.println("\nActive Restaurants:");

            for (Restaurant r : activeRestaurants) {
                System.out.println(r.getName());
            }


            // 5. Update Restaurant
            savedRestaurant.setRating(4.8);

            boolean updated =
                    service.updateRestaurant(savedRestaurant);

            System.out.println("\nRestaurant Updated: " + updated);


            // 6. Delete Restaurant
            // Uncomment only when you want to permanently delete it.
            /*
            boolean deleted =
                    service.deleteRestaurant(restaurantId);

            System.out.println("Restaurant Deleted: " + deleted);
            */

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (IllegalArgumentException e) {

            System.out.println("Validation Error: " + e.getMessage());
        }
    }
}