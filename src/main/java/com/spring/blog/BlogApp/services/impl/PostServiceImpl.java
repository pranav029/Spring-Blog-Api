package com.spring.blog.BlogApp.services.impl;

import com.spring.blog.BlogApp.entities.Category;
import com.spring.blog.BlogApp.entities.Post;
import com.spring.blog.BlogApp.entities.User;
import com.spring.blog.BlogApp.exceptions.ResourceNotFoundException;
import com.spring.blog.BlogApp.payloads.PagedApiResponse;
import com.spring.blog.BlogApp.payloads.PostDto;
import com.spring.blog.BlogApp.repositories.CategoryRepo;
import com.spring.blog.BlogApp.repositories.PostRepo;
import com.spring.blog.BlogApp.repositories.UserRepo;
import com.spring.blog.BlogApp.services.PostService;
import com.spring.blog.BlogApp.utils.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setCreationDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = postRepo.save(post);
        return modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = postRepo.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        postRepo.delete(post);
    }

    @Override
    public PagedApiResponse<List<PostDto>> getAllPosts(Integer pageNumber, Integer pageSize,String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).ascending());
        Page<Post> pagedPosts = postRepo.findAll(pageable);
        List<PostDto> posts = pagedPosts.getContent().stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
        return ResponseUtil.getPagedApiResponse(pagedPosts, posts);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PagedApiResponse<List<PostDto>> getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize,String sortBy) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Post> pagedPosts = postRepo.findByCategory(category, pageable);
        List<PostDto> posts = pagedPosts.getContent().stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return ResponseUtil.getPagedApiResponse(pagedPosts, posts);
    }

    @Override
    public PagedApiResponse<List<PostDto>> getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
        Page<Post> pagedPosts = postRepo.findByUser(user, pageable);
        List<PostDto> posts = pagedPosts.getContent().stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return ResponseUtil.getPagedApiResponse(pagedPosts, posts);
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return null;
    }
}
