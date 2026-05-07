package com.subtle.foodapp.backend.controller;

import com.subtle.foodapp.backend.dto.AddToCartRequest;
import com.subtle.foodapp.backend.entity.Cart;
import com.subtle.foodapp.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(
            @RequestBody AddToCartRequest dto) {

        return ResponseEntity.ok(service.addToCart(dto));
    }
}
