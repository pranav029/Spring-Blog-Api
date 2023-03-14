package com.spring.blog.BlogApp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(nullable = false,length = 100)
    @Size(max = 100)
    private String title;

    @NotBlank
    private String content;
    private String imageName;

    @Column(nullable = false)
    @NotNull
    private Date creationDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
