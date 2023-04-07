package com.spring.blog.BlogApp.payloads.request;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String userName;
    private String password;
}
