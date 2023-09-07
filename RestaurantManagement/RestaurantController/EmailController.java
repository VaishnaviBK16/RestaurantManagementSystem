package com.example.RestaurantManagement.RestaurantController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RestaurantManagement.RestaurantService.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	@RequestMapping("/")
	
	public ResponseEntity<String> checkEmail()
	{
	emailService.sendEmail("vaishnavikamble450@gmail.com", "Check", "Checking Email");
	return new ResponseEntity<>("Message Send",HttpStatus.CREATED);
	}

}
