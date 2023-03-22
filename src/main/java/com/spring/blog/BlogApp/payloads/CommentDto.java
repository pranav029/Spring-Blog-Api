package com.spring.blog.BlogApp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private int commentId;
    private int userId;
    private int postId;
    private String content;
}
