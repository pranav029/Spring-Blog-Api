package com.spring.blog.BlogApp.repositories;

import com.spring.blog.BlogApp.entities.Comment;
import com.spring.blog.BlogApp.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    Page<Comment> findByPost(Post post, Pageable pageable);
}
