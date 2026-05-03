package com.example.ecommerce.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductResponse {
    private Long productId;
    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;
    private List<CategoryResponse> categories;
}
