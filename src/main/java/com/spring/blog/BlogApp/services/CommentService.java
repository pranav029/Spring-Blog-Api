package com.spring.blog.BlogApp.services;

import com.spring.blog.BlogApp.payloads.request.CommentRequestDto;
import com.spring.blog.BlogApp.payloads.response.CommentResponseDto;

public interface CommentService {
    CommentResponseDto createComment(CommentRequestDto comment, Integer postId, Integer userId);
    void deleteComment(Integer commentId);
}
