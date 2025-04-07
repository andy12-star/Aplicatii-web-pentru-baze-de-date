package com.andy.master.service.impl;

import com.andy.master.model.dto.request.CategoryRequest;
import com.andy.master.model.dto.response.CategoryResponse;
import com.andy.master.model.entity.Category;
import com.andy.master.repository.CategoryRepository;
import com.andy.master.service.api.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategory() {
        return categoryRepository.findAll().stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryResponse.builder().id(category.getId()).name(category.getName()).build();
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder().name(categoryRequest.getName()).build();
        return CategoryResponse.builder()
                .id(categoryRepository.save(category).getId())
                .name(category.getName())
                .build();
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryRequest.getName());
        return CategoryResponse.builder()
                .id(categoryRepository.save(category).getId())
                .name(category.getName())
                .build();
    }

    @Override
    public void deleteCategory(Long id) {
categoryRepository.deleteById(id);
    }

}
