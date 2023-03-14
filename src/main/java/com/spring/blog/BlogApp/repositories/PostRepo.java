package com.spring.blog.BlogApp.repositories;

import com.spring.blog.BlogApp.entities.Category;
import com.spring.blog.BlogApp.entities.Post;
import com.spring.blog.BlogApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
