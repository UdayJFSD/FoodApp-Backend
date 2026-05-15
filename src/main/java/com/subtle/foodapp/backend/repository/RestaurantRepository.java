package com.subtle.foodapp.backend.repository;

import com.subtle.foodapp.backend.entity.Restaurant;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByNameContainingIgnoreCase(
            String keyword);
}
