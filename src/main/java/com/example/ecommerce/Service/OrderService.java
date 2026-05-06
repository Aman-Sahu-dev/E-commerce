package com.example.ecommerce.Service;

import com.example.ecommerce.Auth.AuthRepository;
import com.example.ecommerce.Dto.OrderItemRequest;
import com.example.ecommerce.Dto.OrderItemsResponse;
import com.example.ecommerce.Dto.OrderResponse;
import com.example.ecommerce.Dto.OrdersRequest;
import com.example.ecommerce.Model.OrderItems;
import com.example.ecommerce.Model.Orders;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Repositories.OrderRepository;
import com.example.ecommerce.Repositories.ProductRepository;
import com.example.ecommerce.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AuthRepository authRepository;
    public OrderResponse createOrder(OrdersRequest request){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = authRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("user doesnt found"));
        List<OrderItems> orderItems = request.getItems().stream()
                        .map(orderItemRequest -> {
                            Product product = productRepository.findById(orderItemRequest.getProductId())
                                    .orElseThrow(()-> new RuntimeException("product not found"));
                            return OrderItems.builder()
                                    .product(product)
                                    .quantity(orderItemRequest.getQuantity())
                                    .priceAtPurchase(product.getPrice())
                                    .build();
                        }).toList();
        BigDecimal totalPrice = orderItems.stream()
                        .map(item -> item.getPriceAtPurchase()
                                .multiply(BigDecimal.valueOf(item.getQuantity())))
                                .reduce(BigDecimal.ZERO,BigDecimal::add);
        Orders order = Orders.builder()
                        .user(user)
                                .status("Pending")
                                        .items(orderItems)
                                                .created_at(LocalDateTime.now())
                                                        .price(totalPrice)
                                                                .build();
        Orders saved = orderRepository.save(order);
        List<OrderItemsResponse> itemsResponses = saved.getItems().stream()
                .map(item -> new OrderItemsResponse(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPriceAtPurchase()
                )).toList();
        return new OrderResponse(
                saved.getId(),
                saved.getStatus(),
                itemsResponses,
                saved.getPrice());
    }
}
