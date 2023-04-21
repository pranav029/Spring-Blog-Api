package com.spring.blog.BlogApp.services.impl;

import com.spring.blog.BlogApp.entities.User;
import com.spring.blog.BlogApp.exceptions.ResourceNotFoundException;
import com.spring.blog.BlogApp.payloads.request.UserRequestDto;
import com.spring.blog.BlogApp.payloads.response.PagedApiResponse;
import com.spring.blog.BlogApp.payloads.response.UserResponseDto;
import com.spring.blog.BlogApp.repositories.UserRepo;
import com.spring.blog.BlogApp.services.UserService;
import com.spring.blog.BlogApp.utils.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserRequestDto createUser(UserRequestDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserRequestDto.class);
    }

    @Override
    public UserRequestDto updateUser(UserRequestDto userDto, Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = userRepo.save(user);
        return modelMapper.map(updatedUser, UserRequestDto.class);
    }

    @Override
    public UserRequestDto getUserById(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        return modelMapper.map(user, UserRequestDto.class);
    }

    @Override
    public PagedApiResponse<List<UserResponseDto>> getAllUsers(Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<User> pagedUsers = userRepo.findAll(pageable);
        List<UserResponseDto> users = pagedUsers.getContent().stream().map(user -> modelMapper.map(user,UserResponseDto.class)).toList();
        return ResponseUtil.getPagedApiResponse(pagedUsers,users);
    }

    @Override
    public void deleteUser(UserRequestDto userDto) {
        User user = userRepo.findById(userDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userDto.getUserId()));
        userRepo.delete(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        userRepo.deleteById(id);
    }
}
