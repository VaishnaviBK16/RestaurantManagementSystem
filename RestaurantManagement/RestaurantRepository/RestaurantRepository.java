package com.example.RestaurantManagement.RestaurantRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RestaurantManagement.Model.Restaurant;

/*The RestaurantRepository interface is a spring Data JPA repository that provides 
	databases access and CRUD operations for the Restaurant entity.
*/

@Repository
public interface RestaurantRepository extends JpaRepository< Restaurant, Integer>{
	
	List<Restaurant> findByRestaurantNameContainingIgnoreCase(String restaurantName);
	List<Restaurant> findByIsVegRestaurant(boolean status);

}
