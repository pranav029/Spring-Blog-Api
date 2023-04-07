package com.spring.blog.BlogApp.controller;

import com.spring.blog.BlogApp.payloads.request.PostRequestDto;
import com.spring.blog.BlogApp.payloads.response.ApiResponse;
import com.spring.blog.BlogApp.payloads.response.ApiResponseWithContent;
import com.spring.blog.BlogApp.payloads.response.PagedApiResponse;
import com.spring.blog.BlogApp.payloads.response.PostResponseDto;
import com.spring.blog.BlogApp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/create/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<ApiResponseWithContent<PostResponseDto>> createPost(@RequestBody PostRequestDto postDto,
                                                                              @PathVariable Integer userId,
                                                                              @PathVariable Integer categoryId) {

        PostResponseDto createdPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(new ApiResponseWithContent<>("Post created successfully", true, createdPost), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PagedApiResponse<?>> getPostsByUser(
            @PathVariable Integer userId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy
    ) {
        PagedApiResponse<List<PostResponseDto>> response = postService.getPostsByUser(userId, pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PagedApiResponse<?>> getPostsByCategory(
            @PathVariable Integer categoryId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy
    ) {
        PagedApiResponse<List<PostResponseDto>> response = postService.getPostsByCategory(categoryId, pageNumber, pageSize, sortBy);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/posts/all")
    public ResponseEntity<PagedApiResponse<List<PostResponseDto>>> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy
    ) {
        PagedApiResponse<List<PostResponseDto>> response = postService.getAllPosts(pageNumber, pageSize,sortBy);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<ApiResponseWithContent<PostResponseDto>> getPostById(@PathVariable Integer postId) {
        PostResponseDto post = postService.getPostById(postId);
        return ResponseEntity.ok(new ApiResponseWithContent<>("Query Successfull", true, post));
    }

    @DeleteMapping("/delete/{postId}/posts")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(new ApiResponse("Post deleted Successfully", true));
    }

    @PutMapping("/update/{postId}/posts")
    public ResponseEntity<ApiResponseWithContent<PostResponseDto>> updatePost(@RequestBody PostRequestDto postDto, @PathVariable Integer postId) {
        PostResponseDto updatedPost = postService.updatePost(postDto, postId);
        return ResponseEntity.ok(new ApiResponseWithContent<>("Post updated Successfully", true, updatedPost));
    }

    @GetMapping("/posts")
    public ResponseEntity<PagedApiResponse<List<PostResponseDto>>> searchPost(
            @RequestParam(value = "q",required = true) String q,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy
    ){
        PagedApiResponse<List<PostResponseDto>> response = postService.searchPost(q,pageNumber,pageSize,sortBy);
        return ResponseEntity.ok(response);
    }
}
