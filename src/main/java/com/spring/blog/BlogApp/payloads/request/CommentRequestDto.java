package com.spring.blog.BlogApp.payloads.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CommentRequestDto {
    private String content;
}
