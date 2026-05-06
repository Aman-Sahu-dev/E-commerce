package com.example.ecommerce.Dto;

import com.example.ecommerce.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
public class OrderItemsResponse {
    private Long productId;
    private String name;
    private int quantity;
    private BigDecimal price;
}
