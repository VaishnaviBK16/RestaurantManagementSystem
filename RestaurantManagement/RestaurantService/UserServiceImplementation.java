package com.example.RestaurantManagement.RestaurantService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RestaurantManagement.Model.User;
import com.example.RestaurantManagement.RestaurantInterface.UserService;
import com.example.RestaurantManagement.RestaurantRepository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	public void addUser(User obj) {
		
		this.userRepo.save(obj);
	}
	
	public List<User> findBookedUsersByRestId(long restaurantId) {
		
		return this.userRepo.findUsersByRestId(restaurantId);
	}

}
