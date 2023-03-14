package com.spring.blog.BlogApp.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(length = 100,nullable = false)
    private String categoryTitle;
    private String CategoryDescription;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
