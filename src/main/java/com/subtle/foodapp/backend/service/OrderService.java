package com.subtle.foodapp.backend.service;

import com.subtle.foodapp.backend.entity.Order;
import com.subtle.foodapp.backend.entity.OrderItem;
import com.subtle.foodapp.backend.repository.OrderItemRepository;
import com.subtle.foodapp.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepo;
    private final OrderItemRepository itemRepo;

    public Order placeOrder(Order order, List<OrderItem> items) {

        Order saved = orderRepo.save(order);

        for (OrderItem item : items) {
            item.setOrder(saved);
            itemRepo.save(item);
        }

        return saved;
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepo.findByUserId(userId);
    }
}

