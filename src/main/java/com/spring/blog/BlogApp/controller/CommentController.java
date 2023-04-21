package com.spring.blog.BlogApp.controller;

import com.spring.blog.BlogApp.payloads.request.CommentRequestDto;
import com.spring.blog.BlogApp.payloads.response.ApiResponse;
import com.spring.blog.BlogApp.payloads.response.ApiResponseWithContent;
import com.spring.blog.BlogApp.payloads.response.CommentResponseDto;
import com.spring.blog.BlogApp.payloads.response.PagedApiResponse;
import com.spring.blog.BlogApp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/user/{userId}/comment")
    public ResponseEntity<ApiResponseWithContent<CommentResponseDto>> createComment(
            @RequestBody CommentRequestDto commentDto,
            @PathVariable("postId") Integer postId,
            @PathVariable("userId") Integer userId
    ) {
        CommentResponseDto newComment = commentService.createComment(commentDto, postId, userId);
        return ResponseEntity.ok(new ApiResponseWithContent<>("Comment added successfully", true, newComment));
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("postId") Integer commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(new ApiResponse("Comment deleted successfully", true));
    }

    @PostMapping("comment/all/{postId}")
    public ResponseEntity<PagedApiResponse<List<CommentResponseDto>>> getAllCommentsForPost(
            @PathVariable("postId") Integer postId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "commentId", required = false) String sortBy
    ) {
        return ResponseEntity.ok(commentService.getAllCommentsForPost(postId, pageNumber, pageSize, sortBy));
    }
}
