package com.spring.blog.BlogApp.services.impl;

import com.spring.blog.BlogApp.entities.User;
import com.spring.blog.BlogApp.exceptions.ResourceNotFoundException;
import com.spring.blog.BlogApp.payloads.UserDto;
import com.spring.blog.BlogApp.repositories.UserRepo;
import com.spring.blog.BlogApp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = userRepo.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(UserDto userDto) {
        User user = userRepo.findById(userDto.getId()).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userDto.getId()));
        userRepo.delete(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        userRepo.deleteById(id);
    }
}
