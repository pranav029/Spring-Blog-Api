package com.spring.blog.BlogApp.services.impl;

import com.spring.blog.BlogApp.entities.Comment;
import com.spring.blog.BlogApp.entities.Post;
import com.spring.blog.BlogApp.entities.User;
import com.spring.blog.BlogApp.exceptions.ResourceNotFoundException;
import com.spring.blog.BlogApp.payloads.CommentDto;
import com.spring.blog.BlogApp.repositories.CommentRepo;
import com.spring.blog.BlogApp.repositories.PostRepo;
import com.spring.blog.BlogApp.repositories.UserRepo;
import com.spring.blog.BlogApp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto comment, Integer postId,Integer userId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        Comment commentToSave = modelMapper.map(comment,Comment.class);
        commentToSave.setPost(post);
        commentToSave.setUser(user);
        Comment newComment = commentRepo.save(commentToSave);
        return modelMapper.map(newComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId));
        commentRepo.delete(comment);
    }
}
