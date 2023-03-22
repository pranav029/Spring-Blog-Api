package com.spring.blog.BlogApp.services;

import com.spring.blog.BlogApp.entities.Comment;
import com.spring.blog.BlogApp.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto comment, Integer postId,Integer userId);
    void deleteComment(Integer commentId);
}
