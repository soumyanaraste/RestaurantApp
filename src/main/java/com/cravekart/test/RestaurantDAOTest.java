package com.cravekart.test;

import com.cravekart.dao.impl.RestaurantDAOImpl;
import com.cravekart.model.Restaurant;

public class RestaurantDAOTest {

    public static void main(String[] args) {

        try {
            RestaurantDAOImpl dao = new RestaurantDAOImpl();

            Restaurant restaurant = new Restaurant();

            restaurant.setName("Food Palace");
            restaurant.setCuisineType("Indian");
            restaurant.setDeliveryTime(30);
            restaurant.setAddress("Bangalore");
            restaurant.setRating(4.5);
            restaurant.setActive(true);
            restaurant.setImagePath("images/foodpalace.jpg");

            int id = dao.addRestaurant(restaurant);

            System.out.println("Restaurant added successfully!");
            System.out.println("Restaurant ID: " + id);

            Restaurant savedRestaurant = dao.getRestaurantById(id);

            if (savedRestaurant != null) {
                System.out.println("Name: " + savedRestaurant.getName());
                System.out.println("Cuisine: "
                        + savedRestaurant.getCuisineType());
                System.out.println("Rating: "
                        + savedRestaurant.getRating());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}