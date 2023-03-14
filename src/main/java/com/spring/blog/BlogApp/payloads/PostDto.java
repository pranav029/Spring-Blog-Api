package com.spring.blog.BlogApp.payloads;

import com.spring.blog.BlogApp.entities.Category;
import com.spring.blog.BlogApp.entities.User;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date creationDate;
    private CategoryDto category;
    private UserDto user;
}
