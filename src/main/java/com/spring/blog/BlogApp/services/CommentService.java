package com.spring.blog.BlogApp.services;

import com.spring.blog.BlogApp.payloads.request.CommentRequestDto;
import com.spring.blog.BlogApp.payloads.response.CommentResponseDto;
import com.spring.blog.BlogApp.payloads.response.PagedApiResponse;

import java.util.List;

public interface CommentService {
    CommentResponseDto createComment(CommentRequestDto comment, Integer postId, Integer userId);

    void deleteComment(Integer commentId);

    PagedApiResponse<List<CommentResponseDto>> getAllCommentsForPost(Integer postId, Integer pageNumber, Integer pageSize, String sortBy);
}
