package com.subtle.foodapp.backend.controller;

import com.subtle.foodapp.backend.entity.Restaurant;
import com.subtle.foodapp.backend.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService service;

    @GetMapping
    public List<Restaurant> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Restaurant add(@RequestBody Restaurant restaurant) {
        return service.save(restaurant);
    }
}

