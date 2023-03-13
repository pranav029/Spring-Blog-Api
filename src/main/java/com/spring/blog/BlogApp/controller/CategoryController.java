package com.spring.blog.BlogApp.controller;

import com.spring.blog.BlogApp.payloads.ApiResponse;
import com.spring.blog.BlogApp.payloads.CategoryDto;
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
    public ResponseEntity<ApiResponse<CategoryDto>> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(new ApiResponse<>("Category Created successfully", true, createdCategory), HttpStatus.CREATED);
    }

    @PutMapping("/update/{catId}")
    public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto,catId);
        return new ResponseEntity<>(new ApiResponse<>("Category Updated Successfully",true,updatedCategory),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{catId}")
    public ResponseEntity<ApiResponse<?>> deleteCategory(@PathVariable Integer catId){
        categoryService.deleteCategory(catId);
        return ResponseEntity.ok(new ApiResponse<>("Category deleted successfully",true,null));
    }

    @GetMapping("/{catId}")
    public ResponseEntity<ApiResponse<CategoryDto>> getCategory(@PathVariable Integer catId){
        CategoryDto categoryDto = categoryService.getCategory(catId);
        return ResponseEntity.ok(new ApiResponse<>("Query Successful",true,categoryDto));
    }
    @GetMapping("/all-categories")
    public ResponseEntity<ApiResponse<?>> getAllCategory(){
        List<CategoryDto> categories = categoryService.getCategories();
        return ResponseEntity.ok(new ApiResponse<>("Query Successful",true,categories));
    }

}
