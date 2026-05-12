package com.subtle.foodapp.backend.controller;

import com.subtle.foodapp.backend.dto.RestaurantRequest;
import com.subtle.foodapp.backend.entity.Restaurant;
import com.subtle.foodapp.backend.response.ApiResponse;
import com.subtle.foodapp.backend.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Restaurant>> create(
            @Valid @RequestBody RestaurantRequest dto) {

        Restaurant restaurant = service.create(dto);

        ApiResponse<Restaurant> response =
                new ApiResponse<>(
                        true,
                        "Restaurant created successfully",
                        restaurant
                );

        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<Page<Restaurant>>> getAll(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size) {

        Page<Restaurant> restaurants =
                service.getAll(page, size);

        ApiResponse<Page<Restaurant>> response =
                new ApiResponse<>(
                        true,
                        "Restaurants fetched successfully",
                        restaurants
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Restaurant>>> search(

            @RequestParam String keyword) {

        List<Restaurant> restaurants =
                service.search(keyword);

        ApiResponse<List<Restaurant>> response =
                new ApiResponse<>(
                        true,
                        "Search results",
                        restaurants
                );

        return ResponseEntity.ok(response);
    }
}
