package com.spring.blog.BlogApp.repositories;

import com.spring.blog.BlogApp.entities.Follower;
import com.spring.blog.BlogApp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepo extends JpaRepository<Follower,Integer> {
    Page<Follower> findAllByUserFollowed(User userFollowed, Pageable pageable);
}
