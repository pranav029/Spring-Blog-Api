package com.spring.blog.BlogApp.services;

import com.spring.blog.BlogApp.payloads.request.PostRequestDto;
import com.spring.blog.BlogApp.payloads.response.PagedApiResponse;
import com.spring.blog.BlogApp.payloads.response.PostResponseDto;

import java.util.List;

public interface PostService {

    PostResponseDto createPost(PostRequestDto postDto, Integer userId, Integer categoryId);

    PostResponseDto updatePost(PostRequestDto postDto, Integer postId);

    void deletePost(Integer postId);

    PagedApiResponse<List<PostResponseDto>> getAllPosts(Integer pageNumber, Integer pageSize, String sortBy);

    PostResponseDto getPostById(Integer postId);

    PagedApiResponse<List<PostResponseDto>> getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy);

    PagedApiResponse<List<PostResponseDto>> getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy);

    PagedApiResponse<List<PostResponseDto>> searchPost(String keyword, Integer pageNumber, Integer pageSize, String sortBy);
}
