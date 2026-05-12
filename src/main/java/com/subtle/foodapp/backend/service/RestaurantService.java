package com.subtle.foodapp.backend.service;

import com.subtle.foodapp.backend.dto.RestaurantRequest;
import com.subtle.foodapp.backend.entity.Restaurant;
import com.subtle.foodapp.backend.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RestaurantService {

    private final RestaurantRepository repo;

    public RestaurantService(RestaurantRepository repo) {
        this.repo = repo;
    }

    public Restaurant create(RestaurantRequest dto) {

        Restaurant restaurant = new Restaurant();

        restaurant.setName(dto.getName());
        restaurant.setLocation(dto.getLocation());
        restaurant.setRating(dto.getRating());

        return repo.save(restaurant);
    }

    public Page<Restaurant> getAll(
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return repo.findAll(pageable);
    }

    public List<Restaurant> search(String keyword) {

        return repo.findByNameContainingIgnoreCase(
                keyword
        );
    }
}

