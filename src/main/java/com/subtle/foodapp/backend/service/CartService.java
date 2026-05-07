package com.subtle.foodapp.backend.service;

import com.subtle.foodapp.backend.dto.AddToCartRequest;
import com.subtle.foodapp.backend.entity.Cart;
import com.subtle.foodapp.backend.entity.CartItem;
import com.subtle.foodapp.backend.entity.FoodItem;
import com.subtle.foodapp.backend.entity.User;
import com.subtle.foodapp.backend.exception.ResourceNotFoundException;
import com.subtle.foodapp.backend.repository.CartItemRepository;
import com.subtle.foodapp.backend.repository.CartRepository;
import com.subtle.foodapp.backend.repository.FoodItemRepository;
import com.subtle.foodapp.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepo;
    private final FoodItemRepository foodRepo;
    private final CartItemRepository cartItemRepo;
    private final UserRepository userRepo;

    public CartService(
            CartRepository cartRepo,
            FoodItemRepository foodRepo,
            CartItemRepository cartItemRepo,
            UserRepository userRepo) {

        this.cartRepo = cartRepo;
        this.foodRepo = foodRepo;
        this.cartItemRepo = cartItemRepo;
        this.userRepo = userRepo;
    }

    public Cart addToCart(AddToCartRequest dto) {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        FoodItem food = foodRepo.findById(dto.getFoodId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Food item not found"));

        Cart cart = cartRepo.findByUserId(dto.getUserId())
                .orElseGet(() -> {

                    Cart newCart = new Cart();

                    newCart.setUser(user);
                    newCart.setTotalAmount(0.0);

                    return cartRepo.save(newCart);
                });

        CartItem item = new CartItem();

        item.setCart(cart);
        item.setFoodItem(food);
        item.setQuantity(dto.getQuantity());

        cartItemRepo.save(item);

        cart.getItems().add(item);

        double total = cart.getItems()
                .stream()
                .mapToDouble(i ->
                        i.getFoodItem().getPrice()
                                * i.getQuantity())
                .sum();

        cart.setTotalAmount(total);

        return cartRepo.save(cart);
    }
}