package com.subtle.foodapp.backend.service;

import com.subtle.foodapp.backend.entity.FoodItem;
import com.subtle.foodapp.backend.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodItemRepository repo;

    public List<FoodItem> getByRestaurant(Long id) {
        return repo.findByRestaurantId(id);
    }

    public FoodItem save(FoodItem item) {
        return repo.save(item);
    }
}
