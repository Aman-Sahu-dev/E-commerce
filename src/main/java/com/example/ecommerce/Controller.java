package com.example.ecommerce;

import com.example.ecommerce.Dto.*;
import com.example.ecommerce.Service.CategoryService;
import com.example.ecommerce.Service.OrderService;
import com.example.ecommerce.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/removeitem/{orderId}/{itemId}")
    public ResponseEntity<OrderResponse> removeItemFromOrder(@PathVariable Long orderId, @PathVariable Long itemId){
       return ResponseEntity.ok(orderService.removeItemFromOrder(orderId,itemId));
    }

    @PostMapping("/additem/{orderId}")
    public ResponseEntity<OrderResponse> addItemToOrder(@PathVariable Long orderId,@RequestBody OrderItemRequest request){
        return ResponseEntity.ok(orderService.addItemToOrder(orderId,request));
    }
    @PostMapping("/addproduct")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody CreateProductRequest request){
        return ResponseEntity.status(201).body(productService.addProduct(request));
    }
    @PatchMapping("/updateproduct/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest request){
        return ResponseEntity.ok(productService.updateProduct(id,request));
    }
    @PostMapping("/addcategory")
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategorySelectRequest request){
        return ResponseEntity.status(201).body(categoryService.addCategory(request));
    }
    @GetMapping("/getcategories")
    public List<CategoryResponse> getAlCategories(){
        return categoryService.categories();
    }
    @GetMapping("/getproducts")
    public List<ProductResponse> allProducts(){
        return productService.getProducts();
    }
}
