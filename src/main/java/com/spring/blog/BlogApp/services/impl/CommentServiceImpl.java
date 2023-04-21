package com.spring.blog.BlogApp.services.impl;

import com.spring.blog.BlogApp.entities.Comment;
import com.spring.blog.BlogApp.entities.Post;
import com.spring.blog.BlogApp.entities.User;
import com.spring.blog.BlogApp.exceptions.ResourceNotFoundException;
import com.spring.blog.BlogApp.payloads.request.CommentRequestDto;
import com.spring.blog.BlogApp.payloads.response.CommentResponseDto;
import com.spring.blog.BlogApp.payloads.response.PagedApiResponse;
import com.spring.blog.BlogApp.repositories.CommentRepo;
import com.spring.blog.BlogApp.repositories.PostRepo;
import com.spring.blog.BlogApp.repositories.UserRepo;
import com.spring.blog.BlogApp.services.CommentService;
import com.spring.blog.BlogApp.utils.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public CommentResponseDto createComment(CommentRequestDto comment, Integer postId, Integer userId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Comment commentToSave = modelMapper.map(comment, Comment.class);
        commentToSave.setPost(post);
        commentToSave.setUser(user);
        Comment newComment = commentRepo.save(commentToSave);
        return modelMapper.map(newComment, CommentResponseDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        commentRepo.delete(comment);
    }

    @Override
    public PagedApiResponse<List<CommentResponseDto>> getAllCommentsForPost(Integer postId, Integer pageNumber, Integer pageSize, String sortBy) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Comment> pagedComments = commentRepo.findByPost(post, pageable);
        List<CommentResponseDto> comments = pagedComments.getContent().stream().map(comment -> modelMapper.map(comment, CommentResponseDto.class)).toList();
        return ResponseUtil.getPagedApiResponse(pagedComments, comments);
    }
}
