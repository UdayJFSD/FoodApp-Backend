package com.subtle.foodapp.backend.repository;

import com.subtle.foodapp.backend.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {}
