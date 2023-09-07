package com.example.RestaurantManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/* @SpringBootApplication: A convenience annotation that combines @Configuration
@EnableConfiguration and @ComponentScan. It enables auto-configuration and 
component scanning within the package and its sub-packages.

*/

@SpringBootApplication
public class RestApiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiProjectApplication.class, args);
	}

}


