package com.subtle.foodapp.backend.controller;

import com.subtle.foodapp.backend.entity.Cart;
import com.subtle.foodapp.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService service;

    @PostMapping("/add")
    public Cart addToCart(
            @RequestParam Long userId,
            @RequestParam Long foodId,
            @RequestParam Integer qty) {

        return service.addToCart(userId, foodId, qty);
    }
}
