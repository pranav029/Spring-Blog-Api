package com.spring.blog.BlogApp.payloads.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostRequestDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
}
