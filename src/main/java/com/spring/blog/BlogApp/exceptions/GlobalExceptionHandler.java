package com.spring.blog.BlogApp.exceptions;


import com.spring.blog.BlogApp.payloads.ApiResponse;
import com.spring.blog.BlogApp.payloads.ApiResponseWithContent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
        return new ResponseEntity<>(new ApiResponse(e.getMessage(),false), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseWithContent<?>> methodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        return new ResponseEntity<>(new ApiResponseWithContent<>("Fields not valid",false,errors),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> invalidUserPasswordException(BadCredentialsException e){
        return new ResponseEntity<>(new ApiResponse("Invalid username or password!",false),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse> jwtException(AuthenticationException e){
        return new ResponseEntity<>(new ApiResponse(e.getMessage(),false),HttpStatus.UNAUTHORIZED);
    }
}
