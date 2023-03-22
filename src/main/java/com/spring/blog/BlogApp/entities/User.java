package com.spring.blog.BlogApp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(nullable = false,length = 100)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
