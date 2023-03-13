package com.spring.blog.BlogApp.services;

import com.spring.blog.BlogApp.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer id);
    UserDto getUserById(Integer id);
    List<UserDto> getAllUsers();
    void deleteUser(Integer id);
    void deleteUser(UserDto userDto);
}
