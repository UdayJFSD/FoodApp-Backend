package com.subtle.foodapp.backend.service;

import com.subtle.foodapp.backend.dto.RestaurantRequest;
import com.subtle.foodapp.backend.entity.Restaurant;
import com.subtle.foodapp.backend.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RestaurantService {

    private final RestaurantRepository repo;

    public RestaurantService(RestaurantRepository repo) {
        this.repo = repo;
    }

    @CacheEvict(
            value = "restaurants",
            allEntries = true
    )
    public Restaurant create(
            RestaurantRequest dto) {

        Restaurant restaurant =
                new Restaurant();

        restaurant.setName(dto.getName());
        restaurant.setLocation(dto.getLocation());
        restaurant.setRating(dto.getRating());

        return repo.save(restaurant);
    }

    @Cacheable("restaurants")
    public Page<Restaurant> getAll(int page, int size) {

        System.out.println(
                "Fetching from DATABASE..."
        );

        return (Page<Restaurant>) repo.findAll();
    }

    public List<Restaurant> search(String keyword) {

        return repo.findByNameContainingIgnoreCase(
                keyword
        );
    }
}

