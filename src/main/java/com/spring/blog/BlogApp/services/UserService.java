package com.spring.blog.BlogApp.services;

import com.spring.blog.BlogApp.payloads.request.UserRequestDto;
import com.spring.blog.BlogApp.payloads.response.FollowerResponseDto;
import com.spring.blog.BlogApp.payloads.response.PagedApiResponse;
import com.spring.blog.BlogApp.payloads.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userDto);
    UserResponseDto updateUser(UserRequestDto userDto, Integer id);
    UserResponseDto getUserById(Integer id);
    PagedApiResponse<List<UserResponseDto>> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy);
    void deleteUser(Integer id);
    void deleteUser(UserRequestDto userDto);
    PagedApiResponse<FollowerResponseDto> getAllFollowers(Integer userId,Integer pageNumber,Integer pageSize,String sortBy);

    void addFollower(Integer userId, Integer followerId);
}
