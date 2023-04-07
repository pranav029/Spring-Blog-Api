package com.spring.blog.BlogApp.payloads.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserRequestDto {
    private int userId;
    @NotEmpty
    @Size(min = 5,message = "Username must be minimum 5 character long")
    private String name;
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 4 ,message = "Length of password must greater or equal to 4")
    private String password;
    @NotBlank(message = "About cannot be blank")
    private String about;
}
