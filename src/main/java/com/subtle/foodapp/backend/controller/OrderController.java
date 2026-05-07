package com.subtle.foodapp.backend.controller;

import com.subtle.foodapp.backend.dto.CheckoutRequest;
import com.subtle.foodapp.backend.entity.Order;
import com.subtle.foodapp.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/checkout")
    public Order checkout(@RequestBody CheckoutRequest request) {

        return service.checkout(request.getUserId());
    }
}
