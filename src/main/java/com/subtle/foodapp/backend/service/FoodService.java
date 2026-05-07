package com.subtle.foodapp.backend.service;

import com.subtle.foodapp.backend.dto.FoodRequest;
import com.subtle.foodapp.backend.entity.FoodItem;
import com.subtle.foodapp.backend.entity.Restaurant;
import com.subtle.foodapp.backend.exception.ResourceNotFoundException;
import com.subtle.foodapp.backend.repository.FoodItemRepository;
import com.subtle.foodapp.backend.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class FoodService {

    private final FoodItemRepository foodRepo;
    private final RestaurantRepository restaurantRepo;

    public FoodService(
            FoodItemRepository foodRepo,
            RestaurantRepository restaurantRepo) {

        this.foodRepo = foodRepo;
        this.restaurantRepo = restaurantRepo;
    }

    public FoodItem addFood(FoodRequest dto) {

        Restaurant restaurant = restaurantRepo.findById(dto.getRestaurantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Restaurant not found"));

        FoodItem item = new FoodItem();

        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setRestaurant(restaurant);

        return foodRepo.save(item);
    }

    public List<FoodItem> getMenu(Long restaurantId) {

        return foodRepo.findByRestaurantId(restaurantId);
    }
}
