package com.spring.blog.BlogApp.repositories;

import com.spring.blog.BlogApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
