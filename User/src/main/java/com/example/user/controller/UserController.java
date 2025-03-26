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

    // End point to get notifications by userId using Feign Client
    @GetMapping("/feign/users/{userId}/notifications")
    public List<Notification> getNotificationsByUserIdFeignClient(@PathVariable Long userId) {
        return userService.getNotificationsByUserIdFeignClient(userId);
    }

}