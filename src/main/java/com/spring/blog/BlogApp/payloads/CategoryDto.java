package com.spring.blog.BlogApp.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty(message = "Category title cannot be empty")
    @NotBlank(message = "Category title cannot be blank")
    @Size(min = 5,message = "Title must be greater than or equal to 5")
    private String categoryTitle;

    @NotEmpty(message = "Category description cannot be empty")
    @NotBlank(message = "Category description cannot be blank")
    @Size(min = 5,message = "Description length should be atleast 5")
    private String categoryDescription;
}
