package com.subtle.foodapp.backend.controller;

import com.subtle.foodapp.backend.entity.FoodItem;
import com.subtle.foodapp.backend.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService service;

    @GetMapping("/{restaurantId}")
    public List<FoodItem> getMenu(@PathVariable Long restaurantId) {
        return service.getByRestaurant(restaurantId);
    }

    @PostMapping
    public FoodItem add(@RequestBody FoodItem item) {
        return service.save(item);
    }
}
