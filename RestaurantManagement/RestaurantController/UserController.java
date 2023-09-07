package com.example.RestaurantManagement.RestaurantController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RestaurantManagement.Model.User;
import com.example.RestaurantManagement.RestaurantRepository.RestaurantRepository;
import com.example.RestaurantManagement.RestaurantService.UserServiceImplementation;

//many comments on one emp
//many user book one rest

@RestController
@RequestMapping("/users")
public class UserController {
	//open postman
	@Autowired
	private UserServiceImplementation userServ;
	
	
	@Autowired
	private RestaurantRepository restRepo;
	
	
		@PostMapping("/bookTable/{restId}")
		public ResponseEntity<Map<String,String>> addBooking(@PathVariable("restId") int restId,@RequestBody User user)
		{
		
				User obj=new User();
				Random rand = new Random();
				obj.setUserEmail(user.getUserEmail());
				int userId=rand.nextInt(11111);
				obj.setUserID(userId);
				obj.setRestDetails(this.restRepo.findById( restId).get());
				this.userServ.addUser(obj);
				Map<String,String> response=new HashMap<String,String>();
				response.put("status", "success");
				response.put("message", "Comment added!!");
			
					return new ResponseEntity<Map<String,String>>(response, HttpStatus.CREATED);
			
		}
		
		@GetMapping("/bookedUsers/{restaurantId}")
		public List<User> getCommentsByEmpId(@PathVariable long restaurantId)
		{
			List<User> userList = new ArrayList<User>();
			userList = this.userServ.findBookedUsersByRestId(restaurantId);
			
			return  userList;
			
		}

}