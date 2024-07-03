package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.UserCreationRequest;
import com.devteria.identityservice.dto.request.UserUpdateRequest;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.entity.User;
import com.devteria.identityservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> userApiResponse = new ApiResponse<>();
        userApiResponse.setResult(userService.createUser(request));
        return userApiResponse;
    }

    @GetMapping
    List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId){
        ApiResponse<UserResponse> userResponseApiResponse = new ApiResponse<>();
        userResponseApiResponse.setResult(userService.getUser(userId));
        return userResponseApiResponse;
    }

    @PutMapping("/{userId}")
    ApiResponse<User> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        ApiResponse<User> userApiResponse = new ApiResponse<>();
        userApiResponse.setResult(userService.updateUser(userId, request));
        return userApiResponse;
    }

    @DeleteMapping("/{userId}")
    ApiResponse<Boolean> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setResult(true);
        return apiResponse;
    }
}
