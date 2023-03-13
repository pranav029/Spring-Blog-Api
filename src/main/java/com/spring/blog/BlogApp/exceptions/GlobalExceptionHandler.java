package com.spring.blog.BlogApp.exceptions;

import com.spring.blog.BlogApp.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
        return new ResponseEntity<>(new ApiResponse<>(e.getMessage(),false,null), HttpStatus.NOT_FOUND);
    }
}
