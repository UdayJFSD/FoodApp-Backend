package com.subtle.foodapp.backend.controller;

import com.subtle.foodapp.backend.entity.Order;
import com.subtle.foodapp.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return service.placeOrder(order, new ArrayList<>());
    }

    @GetMapping("/{userId}")
    public List<Order> getOrders(@PathVariable Long userId) {
        return service.getUserOrders(userId);
    }
}

