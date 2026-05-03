package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.CreateProductRequest;
import com.example.ecommerce.Dto.ProductResponse;
import com.example.ecommerce.Model.Category;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Repositories.CategoryRepository;
import com.example.ecommerce.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductResponse addProduct(CreateProductRequest request){
        List<Category> categories = categoryRepository.findById(request.getCategoryIds()).orElseThrow(()-> new RuntimeException("couldn't find the categories"));
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .createdAt(LocalDateTime.now())
                .categories(categories)
                .stockQuantity(request.getQuantity())
                .build();
        productRepository.save(product);
        
    }

}
