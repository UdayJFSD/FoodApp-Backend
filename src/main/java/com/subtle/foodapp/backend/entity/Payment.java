package com.subtle.foodapp.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razorpayOrderId;

    private String razorpayPaymentId;

    private String razorpaySignature;

    private Double amount;

    private String status;

    private String paymentMethod;

    private LocalDateTime paymentTime;

    @OneToOne
    private Order order;


}