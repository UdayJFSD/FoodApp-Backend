package com.subtle.foodapp.backend.dto;

import lombok.Data;

@Data
public class AddToCartRequest {

    private Long userId;
    private Long foodId;
    private Integer quantity;
}
