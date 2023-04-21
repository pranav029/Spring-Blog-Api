package com.spring.blog.BlogApp.controller;

import com.spring.blog.BlogApp.payloads.response.ApiResponseWithContent;
import com.spring.blog.BlogApp.payloads.CategoryDto;
import com.spring.blog.BlogApp.payloads.response.PagedApiResponse;
import com.spring.blog.BlogApp.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponseWithContent<CategoryDto>> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(new ApiResponseWithContent<>("Category Created successfully", true, createdCategory), HttpStatus.CREATED);
    }

    @PutMapping("/update/{catId}")
    public ResponseEntity<ApiResponseWithContent<CategoryDto>> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId) {
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, catId);
        return new ResponseEntity<>(new ApiResponseWithContent<>("Category Updated Successfully", true, updatedCategory), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{catId}")
    public ResponseEntity<ApiResponseWithContent<?>> deleteCategory(@PathVariable Integer catId) {
        categoryService.deleteCategory(catId);
        return ResponseEntity.ok(new ApiResponseWithContent<>("Category deleted successfully", true, null));
    }

    @GetMapping("/{catId}")
    public ResponseEntity<ApiResponseWithContent<CategoryDto>> getCategory(@PathVariable Integer catId) {
        CategoryDto categoryDto = categoryService.getCategory(catId);
        return ResponseEntity.ok(new ApiResponseWithContent<>("Query Successful", true, categoryDto));
    }

    @GetMapping("/all-categories")
    public ResponseEntity<PagedApiResponse<List<CategoryDto>>> getAllCategory(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "categoryId", required = false) String sortBy
    ) {
        PagedApiResponse<List<CategoryDto>> response = categoryService.getCategories(pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(response);
    }

}
