package com.example.ecommerce.Dto;

import com.example.ecommerce.Model.Orders;
import com.example.ecommerce.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderItemRequest {
    private int quantity;
    private Long productId;


}
