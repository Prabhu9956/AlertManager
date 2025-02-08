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
    
    @GetMapping("/feign/{notificationId}")
    public Notification getNotificationByIdFeignClient(@PathVariable Long notificationId) {
        return userService.getNotificationByIdFeignClient(notificationId);
    }

    @GetMapping("/feign/users/{userId}")
    public User getUserByIdFeignClient(@PathVariable Long userId) {
        return userService.getUserByIdFeignClient(userId);
    }
    
    @PostMapping("/feign/users")
    public User createUserByFeignClient(@RequestBody User user) {
        return userService.createUserByFeignClient(user);
    }
    
    @PutMapping("/feign/users/{userId}")
    public User updateUserByIdFeignClient(@PathVariable Long userId, @RequestBody User user) {
    	return userService.updateUserByIdFeignClient(userId, user);
    }
    
}
