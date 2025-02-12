package com.example.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.user.entity.Notification;
import com.example.user.entity.User;
import com.example.user.service.impl.UserServiceImpl;
import com.example.user.service.impl.NotificationServiceImpl;

import jakarta.validation.Valid;
import java.util.List;

@Service
@Validated
public class UserService {
    
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;

    // Method to get a user by ID using Feign Client
    public User getUserByIdFeignClient(Long userId) {
        return userServiceImpl.getUserByIdFeignClient(userId);
    }

    // Method to create a user using Feign Client with validation
    public User createUserByFeignClient(@Valid User user) {
        return userServiceImpl.createUserByFeignClient(user);
    }

    // Method to update a user by ID using Feign Client with validation
    public User updateUserByIdFeignClient(Long userId, @Valid User user) {
        return userServiceImpl.updateUserByIdFeignClient(userId, user);
    }

    // Method to get a notification by ID using Feign Client
    public Notification getNotificationByIdFeignClient(Long notificationId) {
        return notificationServiceImpl.getNotificationByIdFeignClient(notificationId);
    }
}
