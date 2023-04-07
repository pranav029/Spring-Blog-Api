package com.spring.blog.BlogApp.payloads.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private int commentId;
    private int userId;
    private int postId;
    private String content;
}
