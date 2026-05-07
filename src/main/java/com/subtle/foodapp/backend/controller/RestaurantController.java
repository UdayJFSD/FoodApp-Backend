package com.subtle.foodapp.backend.controller;

import com.subtle.foodapp.backend.dto.RestaurantRequest;
import com.subtle.foodapp.backend.entity.Restaurant;
import com.subtle.foodapp.backend.service.RestaurantService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Restaurant> create(
            @Valid @RequestBody RestaurantRequest dto) {

        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {

        return ResponseEntity.ok(service.getAll());
    }
}
