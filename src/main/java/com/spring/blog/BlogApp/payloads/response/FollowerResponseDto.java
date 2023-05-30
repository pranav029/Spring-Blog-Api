package com.spring.blog.BlogApp.payloads.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FollowerResponseDto {
    private Integer userId;
    private List<UserResponseDto> userFollower;
}
