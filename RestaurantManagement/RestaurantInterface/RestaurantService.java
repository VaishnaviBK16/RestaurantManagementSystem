package com.example.RestaurantManagement.RestaurantInterface;

import java.util.List;
import java.util.Optional;

import com.example.RestaurantManagement.Model.Restaurant;



/*  Method to save a new Restaurant item to the database
 	Method to retrieve a list of all Restaurant items from the database
 	Method to retrieve  a specific restaurant item by its ID from the database
 	Method to update the details of an existing restaurant item in the database
 	Method to delete a specific restaurant item by its ID from the database
 
  */

public interface RestaurantService {

	Restaurant saveRestaurant(Restaurant restaurant);
	List<Restaurant>getAllRestaurants(String allRestaurant);
	Optional<Restaurant> getRestaurantById(int restaurantId);
	Restaurant updateRestaurantDetails(Optional<Restaurant> existingRestaurant, Restaurant newRestaurantData);
	void deleteRestaurantById(int restaurantId);
	void deleteAllRestaurants();
}