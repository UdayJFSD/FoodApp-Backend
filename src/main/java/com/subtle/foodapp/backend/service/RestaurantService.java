package com.subtle.foodapp.backend.service;

import com.subtle.foodapp.backend.entity.Restaurant;
import com.subtle.foodapp.backend.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository repo;

    public List<Restaurant> getAll() {
        return repo.findAll();
    }

    public Restaurant save(Restaurant restaurant) {
        return repo.save(restaurant);
    }
}

