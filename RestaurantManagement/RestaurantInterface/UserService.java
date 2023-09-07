package com.example.RestaurantManagement.RestaurantInterface;

import java.util.List;

import com.example.RestaurantManagement.Model.User;

public interface UserService {
	
	public void addUser(User obj);
	public List<User> findBookedUsersByRestId(long restaurantId);

}
