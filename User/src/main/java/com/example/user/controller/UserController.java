package com.example.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.user.entity.Notification;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    // End point to get a notification by ID using Feign Client
    @GetMapping("/feign/{notificationId}")
    public Notification getNotificationByIdFeignClient(@PathVariable Long notificationId) {
        return userService.getNotificationByIdFeignClient(notificationId);
    }

    // End point to get a user by ID using Feign Client
    @GetMapping("/feign/users/{userId}")
    public User getUserByIdFeignClient(@PathVariable Long userId) {
        return userService.getUserByIdFeignClient(userId);
    }
    
    // End point to create a user using Feign Client
    @PostMapping("/feign/users")
    public User createUserByFeignClient(@RequestBody User user) {
        return userService.createUserByFeignClient(user);
    }
    
    // End point to update a user by ID using Feign Client
    @PutMapping("/feign/users/{userId}")
    public User updateUserByIdFeignClient(@PathVariable Long userId, @RequestBody User user) {
        return userService.updateUserByIdFeignClient(userId, user);
    }
}
