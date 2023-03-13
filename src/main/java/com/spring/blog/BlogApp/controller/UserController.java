package com.spring.blog.BlogApp.controller;

import com.spring.blog.BlogApp.payloads.ApiResponse;
import com.spring.blog.BlogApp.payloads.UserDto;
import com.spring.blog.BlogApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(new ApiResponse<>("User created successfully", true, createdUser), HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(new ApiResponse<>("User updated Successfully", true, updatedUser));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse<Void>("User deleted successfully", true, null), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDto>> getUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(new ApiResponse<>("Query Successful", true, userService.getUserById(userId)));
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers(){
        return ResponseEntity.ok(new ApiResponse<>("Query Successful",true,userService.getAllUsers()));
    }
}
