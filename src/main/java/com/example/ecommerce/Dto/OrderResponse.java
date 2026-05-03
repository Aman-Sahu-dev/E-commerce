package com.example.ecommerce.Dto;

import com.example.ecommerce.Model.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
public class OrderResponse {
    private Long userId;
    private String status;
    private List<OrderItemsResponse> items;
    private BigDecimal totalPrice;
}
