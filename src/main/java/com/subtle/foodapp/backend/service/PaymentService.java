package com.subtle.foodapp.backend.service;

import com.razorpay.RazorpayClient;
import com.subtle.foodapp.backend.dto.PaymentRequest;
import com.subtle.foodapp.backend.dto.PaymentVerification;
import com.subtle.foodapp.backend.entity.Order;
import com.subtle.foodapp.backend.entity.Payment;
import com.subtle.foodapp.backend.repository.OrderRepository;
import com.subtle.foodapp.backend.repository.PaymentRepository;
import org.apache.commons.codec.digest.HmacUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class PaymentService {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    private final PaymentRepository paymentRepo;
    private final OrderRepository orderRepo;

    public PaymentService(
            PaymentRepository paymentRepo,
            OrderRepository orderRepo) {

        this.paymentRepo = paymentRepo;
        this.orderRepo = orderRepo;
    }

    public String createPaymentOrder(
            PaymentRequest dto)
            throws Exception {

        RazorpayClient client =
                new RazorpayClient(key, secret);

        JSONObject options =
                new JSONObject();

        options.put(
                "amount",
                dto.getAmount() * 100
        );

        options.put(
                "currency",
                "INR"
        );

        options.put(
                "receipt",
                "txn_" + System.currentTimeMillis()
        );

        com.razorpay.Order razorOrder =
                client.orders.create(options);

        return razorOrder.toString();
    }

    public boolean verifyPayment(
            PaymentVerification dto)
            throws Exception {

        String generatedSignature =
                HmacUtils.hmacSha256Hex(
                        secret,

                        dto.getRazorpayOrderId()
                                + "|"
                                + dto.getRazorpayPaymentId()
                );

        if (!generatedSignature.equals(
                dto.getRazorpaySignature())) {

            return false;
        }

        Order order = orderRepo.findById(
                dto.getOrderId()
        ).orElseThrow();

        Payment payment = new Payment();

        payment.setOrder(order);

        payment.setRazorpayOrderId(
                dto.getRazorpayOrderId()
        );

        payment.setRazorpayPaymentId(
                dto.getRazorpayPaymentId()
        );

        payment.setRazorpaySignature(
                dto.getRazorpaySignature()
        );

        payment.setAmount(
                order.getTotalAmount()
        );

        payment.setStatus("SUCCESS");

        payment.setPaymentTime(
                LocalDateTime.now()
        );

        paymentRepo.save(payment);

        order.setStatus("PAID");

        orderRepo.save(order);

        return true;
    }
}
