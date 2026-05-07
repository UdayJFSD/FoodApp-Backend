package com.subtle.foodapp.backend.repository;

import com.subtle.foodapp.backend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}
