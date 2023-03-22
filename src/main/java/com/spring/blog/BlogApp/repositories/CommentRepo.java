package com.spring.blog.BlogApp.repositories;

import com.spring.blog.BlogApp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
