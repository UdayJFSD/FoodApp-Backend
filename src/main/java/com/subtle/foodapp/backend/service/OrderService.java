package com.subtle.foodapp.backend.service;

import com.subtle.foodapp.backend.entity.Cart;
import com.subtle.foodapp.backend.entity.CartItem;
import com.subtle.foodapp.backend.entity.Order;
import com.subtle.foodapp.backend.entity.OrderItem;
import com.subtle.foodapp.backend.repository.CartRepository;
import com.subtle.foodapp.backend.repository.OrderItemRepository;
import com.subtle.foodapp.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository itemRepo;

    @Autowired
    private CartRepository cartRepo;

    public Order checkout(Long userId) {

        Cart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();

        order.setUser(cart.getUser());
        order.setStatus("PLACED");
        order.setOrderTime(LocalDateTime.now());

        double total = 0.0;

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setFoodItem(cartItem.getFoodItem());
            orderItem.setQuantity(cartItem.getQuantity());

            double itemPrice =
                    cartItem.getFoodItem().getPrice()
                            * cartItem.getQuantity();

            orderItem.setPrice(itemPrice);

            total += itemPrice;

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        Order savedOrder = orderRepo.save(order);

        itemRepo.saveAll(orderItems);

        // CLEAR CART
        cart.getItems().clear();
        cart.setTotalAmount(0.0);

        cartRepo.save(cart);

        return savedOrder;
    }
}

