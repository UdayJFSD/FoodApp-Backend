package com.subtle.foodapp.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FoodAppBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(FoodAppBackendApplication.class, args);
	}

}
