package com.spring.blog.BlogApp.controller;

import com.spring.blog.BlogApp.payloads.ApiResponse;
import com.spring.blog.BlogApp.payloads.ApiResponseWithContent;
import com.spring.blog.BlogApp.payloads.CommentDto;
import com.spring.blog.BlogApp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/user/{userId}/comment")
    public ResponseEntity<ApiResponseWithContent<CommentDto>> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable("postId") Integer postId,
            @PathVariable("userId") Integer userId
    ) {
        CommentDto newComment = commentService.createComment(commentDto, postId,userId);
        return ResponseEntity.ok(new ApiResponseWithContent<>("Comment added successfully", true, newComment));
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("postId") Integer commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(new ApiResponse("Comment deleted successfully", true));
    }
}
