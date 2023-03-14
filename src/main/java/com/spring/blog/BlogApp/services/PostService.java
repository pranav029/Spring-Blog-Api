package com.spring.blog.BlogApp.services;

import com.spring.blog.BlogApp.payloads.PagedApiResponse;
import com.spring.blog.BlogApp.payloads.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PagedApiResponse<List<PostDto>> getAllPosts(Integer pageNumber, Integer pageSize);

    PostDto getPostById(Integer postId);

    PagedApiResponse<List<PostDto>> getPostsByCategory(Integer categoryId,Integer pageNumber,Integer pageSize);

    PagedApiResponse<List<PostDto>> getPostsByUser(Integer userId,Integer pageNumber,Integer pageSize);

    List<PostDto> searchPost(String keyword);
}
