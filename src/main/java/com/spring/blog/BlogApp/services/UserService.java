package com.spring.blog.BlogApp.services;

import com.spring.blog.BlogApp.payloads.request.UserRequestDto;

import java.util.List;

public interface UserService {
    UserRequestDto createUser(UserRequestDto userDto);
    UserRequestDto updateUser(UserRequestDto userDto, Integer id);
    UserRequestDto getUserById(Integer id);
    List<UserRequestDto> getAllUsers();
    void deleteUser(Integer id);
    void deleteUser(UserRequestDto userDto);
}
