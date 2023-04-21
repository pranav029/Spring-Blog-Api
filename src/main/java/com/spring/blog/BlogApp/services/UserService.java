package com.spring.blog.BlogApp.services;

import com.spring.blog.BlogApp.payloads.request.UserRequestDto;
import com.spring.blog.BlogApp.payloads.response.PagedApiResponse;
import com.spring.blog.BlogApp.payloads.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserRequestDto createUser(UserRequestDto userDto);
    UserRequestDto updateUser(UserRequestDto userDto, Integer id);
    UserRequestDto getUserById(Integer id);
    PagedApiResponse<List<UserResponseDto>> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy);
    void deleteUser(Integer id);
    void deleteUser(UserRequestDto userDto);
}
