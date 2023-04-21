package com.spring.blog.BlogApp.services;

import com.spring.blog.BlogApp.payloads.CategoryDto;
import com.spring.blog.BlogApp.payloads.response.PagedApiResponse;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    void deleteCategory(Integer categoryId);
    CategoryDto getCategory(Integer categoryId);
    PagedApiResponse<List<CategoryDto>> getCategories(Integer pageNumber, Integer pageSize, String sortBy);
}
