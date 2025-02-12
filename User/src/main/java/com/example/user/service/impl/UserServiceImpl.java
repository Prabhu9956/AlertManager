package com.example.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.user.entity.User;
import com.example.user.exception.UserNotFoundException;
import com.example.user.feignclientinterface.UserServiceFeignClient;
import jakarta.validation.Valid;

@Service
@Validated
public class UserServiceImpl {

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    // Method to get a user by ID using Feign Client
    public User getUserByIdFeignClient(Long userId) {
        User user = userServiceFeignClient.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found with id " + userId);
        }
        return user;
    }

    // Method to create a user using Feign Client with validation
    public User createUserByFeignClient(@Valid User user) {
        return userServiceFeignClient.createUser(user);
    }

    // Method to update a user by ID using Feign Client with validation
    public User updateUserByIdFeignClient(Long userId, @Valid User user) {
        User existingUser = userServiceFeignClient.getUserById(userId);
        if (existingUser == null) {
            throw new UserNotFoundException("User not found with id " + userId);
        }
        return userServiceFeignClient.updateUser(userId, user);
    }
}
