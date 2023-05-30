package com.spring.blog.BlogApp.payloads.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserResponseDto {
    private int userId;
    private String name;
    private String email;
    private String about;
}
