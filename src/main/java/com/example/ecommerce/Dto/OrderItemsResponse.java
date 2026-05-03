package com.example.ecommerce.Dto;

import com.example.ecommerce.Model.Product;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemsResponse {
    private String name;
    private Long productId;
    private int quantity;
    private BigDecimal price;
}
