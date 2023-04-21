package com.spring.blog.BlogApp.controller;

import com.spring.blog.BlogApp.payloads.response.ApiResponseWithContent;
import com.spring.blog.BlogApp.payloads.request.UserRequestDto;
import com.spring.blog.BlogApp.payloads.response.PagedApiResponse;
import com.spring.blog.BlogApp.payloads.response.UserResponseDto;
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
    public ResponseEntity<ApiResponseWithContent<UserRequestDto>> createUser(@Valid @RequestBody UserRequestDto userDto) {
        UserRequestDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(new ApiResponseWithContent<>("User created successfully", true, createdUser), HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponseWithContent<UserRequestDto>> updateUser(@PathVariable Integer userId, @Valid @RequestBody UserRequestDto userDto) {
        UserRequestDto updatedUser = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(new ApiResponseWithContent<>("User updated Successfully", true, updatedUser));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponseWithContent<Void>("User deleted successfully", true, null), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseWithContent<UserRequestDto>> getUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(new ApiResponseWithContent<>("Query Successful", true, userService.getUserById(userId)));
    }

    @GetMapping("/get-all")
    public ResponseEntity<PagedApiResponse<List<UserResponseDto>>> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "userId", required = false) String sortBy
    ) {
        return ResponseEntity.ok(userService.getAllUsers(pageNumber, pageSize, sortBy));
    }
}
