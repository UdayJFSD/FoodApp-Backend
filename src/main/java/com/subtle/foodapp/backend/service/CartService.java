package com.subtle.foodapp.backend.service;

import com.subtle.foodapp.backend.entity.Cart;
import com.subtle.foodapp.backend.entity.CartItem;
import com.subtle.foodapp.backend.entity.FoodItem;
import com.subtle.foodapp.backend.repository.CartItemRepository;
import com.subtle.foodapp.backend.repository.CartRepository;
import com.subtle.foodapp.backend.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private FoodItemRepository foodRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    public Cart addToCart(Long userId, Long foodId, Integer qty) {

        Cart cart = cartRepo.findByUserId(userId)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setItems(new ArrayList<>());
                    c.setTotalAmount(0.0);
                    return cartRepo.save(c);
                });

        FoodItem food = foodRepo.findById(foodId)
                .orElseThrow();

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setFoodItem(food);
        item.setQuantity(qty);

        cartItemRepo.save(item);

        cart.getItems().add(item);

        double total = cart.getItems()
                .stream()
                .mapToDouble(i ->
                        i.getFoodItem().getPrice() * i.getQuantity())
                .sum();

        cart.setTotalAmount(total);

        return cartRepo.save(cart);
    }
}