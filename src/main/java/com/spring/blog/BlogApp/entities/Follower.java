package com.spring.blog.BlogApp.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer relationId;

    private Integer userId;
    @ManyToOne
    @JoinColumn(name = "user_followed")
    private User userFollowed;
    @ManyToOne
    @JoinColumn(name = "user_follower")
    private User userFollower;

    public Follower(Integer userId,User user,User follower){
        this.userId = userId;
        this.userFollowed = user;
        this.userFollower = follower;
    }
}
