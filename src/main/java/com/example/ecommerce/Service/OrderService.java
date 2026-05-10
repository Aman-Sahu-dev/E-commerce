package com.example.ecommerce.Service;

import com.example.ecommerce.Auth.AuthRepository;
import com.example.ecommerce.Dto.OrderItemRequest;
import com.example.ecommerce.Dto.OrderItemsResponse;
import com.example.ecommerce.Dto.OrderResponse;
import com.example.ecommerce.Dto.OrdersRequest;
import com.example.ecommerce.Model.OrderItems;
import com.example.ecommerce.Model.Orders;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Repositories.OrderItemsRepositories;
import com.example.ecommerce.Repositories.OrderRepository;
import com.example.ecommerce.Repositories.ProductRepository;
import com.example.ecommerce.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AuthRepository authRepository;
    private final OrderItemsRepositories orderItemsRepositories;
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
        orderItems.forEach(item->item.setOrders(order));
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
    public void deleteOrder(Long orderId){
        Orders order = orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("order doesnt found"));
        orderRepository.delete(order);
    }
    public OrderResponse addItemToOrder(Long orderId, OrderItemRequest request){
       Orders order = orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("order doesnt found"));
       Product product = productRepository.findById(request.getProductId()).orElseThrow(()-> new RuntimeException("product doesnt found"));
       OrderItems newItem = OrderItems.builder()
               .product(product)
               .orders(order)
               .priceAtPurchase(product.getPrice())
               .quantity(request.getQuantity())
               .build();
       List<OrderItems> items= new ArrayList<>(order.getItems());
       items.add(newItem);
       BigDecimal newTotal = items.stream()
                       .map(item-> item.getPriceAtPurchase().multiply(BigDecimal.valueOf(item.getQuantity())))
                               .reduce(BigDecimal.ZERO,BigDecimal::add);
       order.setItems(items);
       order.setPrice(newTotal);
       Orders saved = orderRepository.save(order);
       List<OrderItemsResponse> itemsResponses = saved.getItems().stream()
               .map(item->new OrderItemsResponse(
                       item.getProduct().getId()
                                ,item.getProduct().getName()
                                        ,item.getQuantity()
                                            ,item.getPriceAtPurchase()
               )).toList();
       return new OrderResponse(saved.getId(), saved.getStatus(),itemsResponses,saved.getPrice());
    }
    public OrderResponse removeItemFromOrder(Long orderId,Long orderItemId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("order doesnt found"));
        List<OrderItems> items = new ArrayList<>(order.getItems());
        items.removeIf(item -> item.getId().equals(orderItemId));
        order.setItems(items);
        BigDecimal total = items.stream()
                .map(item -> item.getPriceAtPurchase()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setPrice(total);
        Orders saved = orderRepository.save(order);
        List<OrderItemsResponse> itemsResponses = saved.getItems().stream()
                .map(item -> new OrderItemsResponse(
                        item.getProduct().getId()
                        , item.getProduct().getName()
                        , item.getQuantity()
                        , item.getPriceAtPurchase()
                )).toList();
        return new OrderResponse(saved.getId(), saved.getStatus(), itemsResponses, saved.getPrice());
    }
}
