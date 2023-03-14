package com.spring.blog.BlogApp.controller;

import com.spring.blog.BlogApp.payloads.ApiResponse;
import com.spring.blog.BlogApp.payloads.PostDto;
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
    public ResponseEntity<ApiResponse<PostDto>> createPost(@RequestBody PostDto postDto,
                                                           @PathVariable Integer userId,
                                                           @PathVariable Integer categoryId) {

        PostDto createdPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(new ApiResponse<>("Post created successfully", true, createdPost), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<ApiResponse<?>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> posts = postService.getPostsByUser(userId);
        if (posts.isEmpty())return ResponseEntity.ok(new ApiResponse<>("No posts found",true,null));
        return ResponseEntity.ok(new ApiResponse<>("Query Successfull",true,posts));
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<ApiResponse<?>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts = postService.getPostsByCategory(categoryId);
        if (posts.isEmpty())return ResponseEntity.ok(new ApiResponse<>("No posts found",true,null));
        return ResponseEntity.ok(new ApiResponse<>("Query Successfull",true,posts));
    }

    @GetMapping(path = {"/posts","/posts/p-{pageSize}"})
    public ResponseEntity<ApiResponse<List<PostDto>>> getAllPosts(){
        List<PostDto> posts = postService.getAllPosts();
        if(posts.isEmpty())return ResponseEntity.ok(new ApiResponse<>("No posts found",true,null));
        return ResponseEntity.ok(new ApiResponse<>("Query Successfull",true,posts));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse<PostDto>> getPostById(@PathVariable Integer postId){
        PostDto post = postService.getPostById(postId);
        return ResponseEntity.ok(new ApiResponse<>("Query Successfull",true,post));
    }

    @DeleteMapping("/delete/{postId}/posts")
    public ResponseEntity<ApiResponse<?>> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return ResponseEntity.ok(new ApiResponse<>("Post deleted Successfully",true,null));
    }

    @PutMapping("/update/{postId}/posts")
    public ResponseEntity<ApiResponse<PostDto>> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatedPost = postService.updatePost(postDto,postId);
        return ResponseEntity.ok(new ApiResponse<>("Post updated Successfully",true,updatedPost));
    }
}
