package com.andy.master.service.api;

import com.andy.master.model.dto.request.CategoryRequest;
import com.andy.master.model.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService{
    List<CategoryResponse> getAllCategory();
    CategoryResponse getCategoryById(Long id);
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(Long id, CategoryRequest request);
    void deleteCategory(Long id);

}
