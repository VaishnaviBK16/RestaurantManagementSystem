package com.example.RestaurantManagement.RestaurantRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.RestaurantManagement.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query(value = "SELECT * FROM User_table p WHERE p.restaurantId = ?1",nativeQuery = true  )
	public List<User> findUsersByRestId(long restaurantId);

}
