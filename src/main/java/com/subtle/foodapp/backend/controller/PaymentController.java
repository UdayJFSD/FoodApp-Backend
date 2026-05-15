package com.subtle.foodapp.backend.controller;

import com.subtle.foodapp.backend.dto.PaymentRequest;
import com.subtle.foodapp.backend.dto.PaymentVerification;
import com.subtle.foodapp.backend.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(
            PaymentService service) {

        this.service = service;
    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(
            @RequestBody PaymentRequest dto)
            throws Exception {

        return ResponseEntity.ok(
                service.createPaymentOrder(dto)
        );
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(
            @RequestBody PaymentVerification dto)
            throws Exception {

        boolean verified =
                service.verifyPayment(dto);

        if (verified) {

            return ResponseEntity.ok(
                    "Payment Verified"
            );
        }

        return ResponseEntity.badRequest()
                .body("Payment Failed");
    }
}
