package com.subtle.foodapp.backend.controller;

import com.subtle.foodapp.backend.dto.RestaurantRequest;
import com.subtle.foodapp.backend.entity.Restaurant;
import com.subtle.foodapp.backend.response.ApiResponse;
import com.subtle.foodapp.backend.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Restaurant APIs",
        description = "Manage restaurants")
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create restaurant",
            description = "Creates a new restaurant"
    )

    @ApiResponses(value = {

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Restaurant created"
            ),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Access denied"
            )
    })
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
