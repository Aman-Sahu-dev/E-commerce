package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.CategoryResponse;
import com.example.ecommerce.Dto.CategorySelectRequest;
import com.example.ecommerce.Model.Category;
import com.example.ecommerce.Repositories.CategoryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<CategoryResponse> categories(){
        return categoryRepository.findAll().stream()
                .map(category -> new CategoryResponse(category.getId(),category.getName()))
                .toList();
    }
public CategoryResponse addCategory(CategorySelectRequest request){
        Category category = Category.builder()
                .name(request.getName())
                .build();
        Category saved = categoryRepository.save(category);
        return new CategoryResponse(saved.getId(),saved.getName());

}
}

