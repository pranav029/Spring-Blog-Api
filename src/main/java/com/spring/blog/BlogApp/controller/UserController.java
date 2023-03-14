package com.spring.blog.BlogApp.controller;

import com.spring.blog.BlogApp.payloads.ApiResponseWithContent;
import com.spring.blog.BlogApp.payloads.UserDto;
import com.spring.blog.BlogApp.services.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ApiResponseWithContent<UserDto>> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(new ApiResponseWithContent<>("User created successfully", true, createdUser), HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponseWithContent<UserDto>> updateUser(@PathVariable Integer userId, @Valid @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(new ApiResponseWithContent<>("User updated Successfully", true, updatedUser));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponseWithContent<Void>("User deleted successfully", true, null), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseWithContent<UserDto>> getUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(new ApiResponseWithContent<>("Query Successful", true, userService.getUserById(userId)));
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponseWithContent<List<UserDto>>> getAllUsers(){
        return ResponseEntity.ok(new ApiResponseWithContent<>("Query Successful",true,userService.getAllUsers()));
    }
}
