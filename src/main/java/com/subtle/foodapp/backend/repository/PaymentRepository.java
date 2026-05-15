package com.subtle.foodapp.backend.repository;

import com.subtle.foodapp.backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}