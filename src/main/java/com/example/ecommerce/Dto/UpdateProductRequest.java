package com.example.ecommerce.Dto;

import com.example.ecommerce.Model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class UpdateProductRequest {
    private String name;
    private String description;
    private Integer stock;
    private BigDecimal price;
    private List<Integer> categories;
}
