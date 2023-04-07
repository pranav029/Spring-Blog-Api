package com.spring.blog.BlogApp.payloads.response;

import com.spring.blog.BlogApp.payloads.CategoryDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date creationDate;
    private CategoryDto category;
    private UserResponseDto user;
    private Set<CommentResponseDto> comments = new HashSet<>();
}
