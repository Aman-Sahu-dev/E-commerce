package com.example.ecommerce.Dto;

import com.example.ecommerce.User.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class OrdersRequest {
    private List<OrderItemRequest> items;
    private BigDecimal amount;
    private String status;
}
