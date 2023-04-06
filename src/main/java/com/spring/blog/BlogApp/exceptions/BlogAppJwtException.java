package com.spring.blog.BlogApp.exceptions;

public class BlogAppJwtException extends RuntimeException{
    public BlogAppJwtException(String message) {
        super(message);
    }
}
