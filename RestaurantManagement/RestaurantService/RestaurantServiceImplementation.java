package com.example.RestaurantManagement.RestaurantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.RestaurantManagement.RestApiProjectApplication;
import com.example.RestaurantManagement.Model.Restaurant;
import com.example.RestaurantManagement.RestaurantRepository.RestaurantRepository;
import com.example.RestaurantManagement.RestaurantController.RestaurantController;
import com.example.RestaurantManagement.RestaurantInterface.RestaurantService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;



/* The RestaurantServiceImplementation class is a service class that provides the business logic 
It implements the RestaurantService interface to perform operations(Insert, Read, Update, Delete) on items.
*/

@Service
public class RestaurantServiceImplementation  implements RestaurantService{
	
	RestaurantRepository restaurantRepository;

	public RestaurantServiceImplementation(RestaurantRepository restRepository) {
		this.restaurantRepository = restRepository;
	}

	//save Restaurant data into database
	public Restaurant saveRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant); 
	}

	//get all Restaurants from Database
	@Override
	public List<Restaurant> getAllRestaurants(String title) {
		List<Restaurant> restaurant = new ArrayList<Restaurant>();

		if (title == null) 
			restaurantRepository.findAll().forEach(restaurant::add);
		else
			restaurantRepository.findByRestaurantNameContainingIgnoreCase(title).forEach(restaurant::add);
		
		return restaurant;
	}

		
	//get Restaurant by id
	@Override
	public Optional<Restaurant> getRestaurantById(int restaurantId) { 
		Optional<Restaurant> restaurant=restaurantRepository.findById(restaurantId);
		return restaurant;
	}
	

	//update restaurant by id through Database
	@Override
	public Restaurant updateRestaurantDetails( Optional<Restaurant> existingRestaurant,Restaurant newRestaurantData) {

		Restaurant restaurant=existingRestaurant.get();
		restaurant.setAvgCost(newRestaurantData.getAvgCost());
		restaurant.setOwnerName(newRestaurantData.getOwnerName());
		restaurant.setRatings(newRestaurantData.getRatings());
		restaurant.setRestaurantName(newRestaurantData.getRestaurantName());
		restaurant.setIsVegRestaurant(newRestaurantData.getIsVegRestaurant());
		restaurant.setLocation(newRestaurantData.getLocation());
		return restaurantRepository.save(restaurant);
	}
	

	//delete restaurant by id from Database
	@Override
	public void deleteRestaurantById(int restaurantId) {
		restaurantRepository.deleteById(restaurantId);
	}


	// get restaurant by name from Database
	public Restaurant getRestaurantByName(int restaurantId) { 
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);  
		System.out.println(restaurantId);
		System.out.println(restaurant.isPresent());
		if(restaurant.isPresent()) {
			Restaurant obj = restaurant.get();
			System.out.println(obj);
			return restaurant.get();
		}
		else {
			return null;
		}
	}

	//Delete All data from Database
		public void deleteAllRestaurants() {
			restaurantRepository.deleteAll();	
		}
		
		//get RestaurantDetails By RestaurantName from Restaurant database
		public List<Restaurant>getByRestaurantDetailsByRestaurantName(String restaurantName) {
			List<Restaurant> RestaurantList = new ArrayList<>();
			restaurantRepository.findByRestaurantNameContainingIgnoreCase(restaurantName).forEach(RestaurantList::add);
			return RestaurantList;
		}
		
		//search NonVegRestaurants and VegRestaurant Restaurant Data to the Database   
		public List<Restaurant> findByIsVegRestaurant(boolean status) {
			return restaurantRepository.findByIsVegRestaurant(status);
		}
		
		
		//sort Rest by Cost	
		public List<Restaurant> getAllRestaurantsByPriceDesc(String direction, String price) {
			List<Restaurant> restaurant = restaurantRepository.findAll(Sort.by( getSortDirection(direction), price));
			return restaurant;
		}

		public List<Restaurant> getAllRestaurantsByRatingsDesc(String direction, String ratings) {
			List<Restaurant> restaurant = restaurantRepository.findAll(Sort.by(getSortDirection(direction), ratings));
			return restaurant;
		}
		
		private Sort.Direction getSortDirection(String direction) {
		    if (direction.equals("asc")) {
		    	System.out.println(" came here");
		      return Sort.Direction.ASC;
		    } else if (direction.equals("desc")) {
		    	System.out.println(" came here desc");
		      return Sort.Direction.DESC;
		    }
		 
		    return Sort.Direction.ASC;
		  }
		
		
	
}
	
