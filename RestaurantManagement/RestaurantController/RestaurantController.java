package com.example.RestaurantManagement.RestaurantController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.RestaurantManagement.Model.Restaurant;
import com.example.RestaurantManagement.RestaurantRepository.RestaurantRepository;
import com.example.RestaurantManagement.RestaurantService.RestaurantServiceImplementation;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")

/* The RestaurantController is a controller class that helps to accept the request 
  	and returns the responses*/

public class RestaurantController {

	@Autowired	
	public RestaurantServiceImplementation restaurantServImp;

	public RestaurantController(RestaurantServiceImplementation restaurantServImp) {
		this.restaurantServImp = restaurantServImp;
	}

	@GetMapping("/restaurant")
	public ResponseEntity<List<Restaurant>> getAllRestaurants(@RequestParam(required = false) String restaurantName) {
		try {
			List<Restaurant> restaurantList = restaurantServImp.getAllRestaurants(restaurantName);
			
			if (restaurantList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(restaurantList, HttpStatus.OK);  
		
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//create restaurant rest api
		@PostMapping("/restaurant/createRestaurant")
		public ResponseEntity<Restaurant> saveRestaurant(@RequestBody Restaurant restaurant){
			System.out.println(restaurant);
			try {
				return new ResponseEntity<>(restaurantServImp.saveRestaurant(restaurant), HttpStatus.CREATED);
			}
			catch(Exception e)
			{
				return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
		}
	
	
		//get any specific restaurant rest api
			@GetMapping("/restaurant/readRestaurant/{id}")
			public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("id")  int restaurantId){
				System.out.println(restaurantId);
				Optional<Restaurant> restaurant = restaurantServImp.getRestaurantById(restaurantId);
					
				if(restaurant.isPresent())
					{
						return new ResponseEntity<>(restaurant.get(),HttpStatus.OK);
					}
					else {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
				}

			//Update any specific restaurant rest api
			@PutMapping("/restaurant/updateRestaurant/{id}")
			public ResponseEntity<Restaurant> updateRestDetailsById(@PathVariable("id") int restaurantId, @RequestBody Restaurant restDetails){
				Optional<Restaurant> restaurantData=restaurantServImp.getRestaurantById(restaurantId);
				
				if(restaurantData.isPresent())
				{
					
					restaurantServImp.updateRestaurantDetails(restaurantData, restDetails);
					return new ResponseEntity<>(restaurantServImp.updateRestaurantDetails(restaurantData, restDetails),HttpStatus.OK);
				}
				else
				{
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
			}

			//Delete specific restaurant rest api
			@DeleteMapping("/restaurant/deleteRestaurant/{id}")	
			public ResponseEntity<String> deleteRestaurantById(@PathVariable("id")  int restaurantId){
				Optional<Restaurant> restaurantData=restaurantServImp.getRestaurantById(restaurantId);

				try {
					if(restaurantData.isPresent())
					{
						restaurantServImp.deleteRestaurantById(restaurantId);
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
					else {
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					}
				}
				catch(Exception e)
				{
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
			}
		
			//deleteAll restaurant rest api
			@DeleteMapping("/restaurant/deleteAllRestaurants")
			public ResponseEntity<HttpStatus> deleteAllRestaurants(){
					try 
					{
						restaurantServImp.deleteAllRestaurants();
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
					catch(Exception exception) {
						return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
			}
		
		
			//search VegRestaurant rest api
			@GetMapping("/restaurant/VegRestaurant")
			public ResponseEntity<List<Restaurant>> findByVegDomain() {
				try {
					List<Restaurant> restaurant = restaurantServImp.findByIsVegRestaurant(true);

					if (restaurant.isEmpty()) {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
					return new ResponseEntity<>(restaurant, HttpStatus.OK);
				} 
				catch (Exception e) {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

			//search NonVegRestaurant rest api
			@GetMapping("/restaurant/nonVegRestaurant")
			public ResponseEntity<List<Restaurant>> findByNonVegDomain() {
				try {

					List<Restaurant> restaurant = restaurantServImp.findByIsVegRestaurant(false);

					if (restaurant.isEmpty()) {
						
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
					return new ResponseEntity<>(restaurant, HttpStatus.OK);
				} 
				catch (Exception e) {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
			
			//sort restaurant by ratings
			@GetMapping("restaurant/filter/Ratings/{direction}")
			public ResponseEntity<List<Restaurant>> getAllRestaurantsByRatingsHighToLow(@PathVariable("direction") String direction, @RequestParam(required = true) String ratings){

				try {
					List<Restaurant> restaurantList = restaurantServImp.getAllRestaurantsByRatingsDesc(direction, ratings);
					if (restaurantList.isEmpty()) {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}

					return new ResponseEntity<>(restaurantList, HttpStatus.OK);
				}
				catch (Exception e) {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}

			}
			
			//sort Restaurant by cost
			
			@GetMapping("restaurant/filter/Price/{direction}")
			public ResponseEntity<List<Restaurant>> getAllRestaurantsByPriceHighToLow(@PathVariable("direction") String direction, @RequestParam(required = true) String price){

				try {
					List<Restaurant> restaurantList = restaurantServImp.getAllRestaurantsByPriceDesc(direction, price);
					System.out.println(direction);
					if (restaurantList.isEmpty()) {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}

					return new ResponseEntity<>(restaurantList, HttpStatus.OK);
				}
				catch (Exception e) {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}

			}
}


