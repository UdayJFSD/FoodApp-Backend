package com.subtle.foodapp.backend.dto;

import lombok.Data;

@Data
public class PaymentVerification {

    private String razorpayOrderId;

    private String razorpayPaymentId;

    private String razorpaySignature;

    private Long orderId;

}