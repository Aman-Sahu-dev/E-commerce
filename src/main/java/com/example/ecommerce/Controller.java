package com.example.ecommerce;

import com.example.ecommerce.Dto.OrderResponse;
import com.example.ecommerce.Dto.OrdersRequest;
import com.example.ecommerce.Service.CategoryService;
import com.example.ecommerce.Service.OrderService;
import com.example.ecommerce.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/web")
@RequiredArgsConstructor
public class Controller {
    private final OrderService orderService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder( @RequestBody OrdersRequest request){
        return ResponseEntity.status(201).body(orderService.createOrder(request));
    }
    @DeleteMapping
    public  void deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
    }
}
