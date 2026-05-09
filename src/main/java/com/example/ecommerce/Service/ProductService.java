package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.CategoryResponse;
import com.example.ecommerce.Dto.CreateProductRequest;
import com.example.ecommerce.Dto.ProductResponse;
import com.example.ecommerce.Dto.UpdateProductRequest;
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
        List<Category> categories = request.getCategoryIds().stream()
                .map(id->{
                      return  categoryRepository.findById(id).orElseThrow(()->new RuntimeException("category doesnt found"));
    }).toList();
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .createdAt(LocalDateTime.now())
                .categories(categories)
                .stockQuantity(request.getQuantity())
                .build();
       Product saved = productRepository.save(product);
       List<CategoryResponse> categoryResponses = saved.getCategories().stream()
               .map(c-> new CategoryResponse(c.getId(),c.getName()))
               .toList();
       return new ProductResponse(
               saved.getId(),
               saved.getName(),
               saved.getDescription(),
               saved.getStockQuantity(),
               saved.getPrice(),
               categoryResponses
       );
        }
        public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("product doesnt found"));
        productRepository.delete(product);
    }
        public ProductResponse updateProduct(Long id,UpdateProductRequest request){
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("product doesnt found"));
        if(request.getName() != null) product.setName(request.getName());
        if(request.getDescription() != null) product.setDescription(request.getDescription());
        if(request.getPrice() != null) product.setPrice(request.getPrice());
        if(request.getStock() != null) product.setStockQuantity(request.getStock());
        if(request.getCategories() != null) {
            List<Category> categories = request.getCategories().stream()
                    .map(categoryId -> categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("category doesnt found")))
                    .toList();
            product.setCategories(categories);
        }
        Product saved = productRepository.save(product);
        List<CategoryResponse> categoryResponses = saved.getCategories().stream()
                .map(c-> new CategoryResponse(c.getId(),c.getName()))
                .toList();
        return new ProductResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getStockQuantity(),
                saved.getPrice(),
                categoryResponses
                );

        }
        public List<ProductResponse> getProducts(){
        return productRepository.findAll().stream()
                .map(product->{
                    List<CategoryResponse> categories = product.getCategories().stream()
                            .map(c-> new CategoryResponse(c.getId(),c.getName()))
                            .toList();
                    return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getStockQuantity(),product.getPrice(),categories);
                }).toList();
        }
    

}
