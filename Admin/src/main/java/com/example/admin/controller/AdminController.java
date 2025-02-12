package com.example.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.admin.entity.Notification;
import com.example.admin.entity.User;
import com.example.admin.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Endpoint to get a notification by ID using Feign Client
    @GetMapping("/feign/notification/{notificationId}")
    public Notification getNotificationByIdFeignClient(@PathVariable Long notificationId) {
        return adminService.getNotificationByIdFeignClient(notificationId);
    }

    // Endpoint to create a notification using Feign Client
    @PostMapping("/feign/notification")
    public Notification createNotificationFeignClient(@RequestBody Notification notification) {
        return adminService.createNotificationFeignClient(notification);
    }

    // Endpoint to get all notifications using Feign Client
    @GetMapping("/feign/notifications")
    public List<Notification> getAllNotificationsFeignClient() {
        return adminService.getAllNotificationsFeignClient();
    }

    // Endpoint to delete a notification by ID using Feign Client
    @DeleteMapping("/feign/notification/{notificationId}")
    public void deleteNotificationByIdFeignClient(@PathVariable Long notificationId) {
        adminService.deleteNotificationByIdFeignClient(notificationId);
    }

    // Endpoint to create a user
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return adminService.createUser(user);
    }

    // Endpoint to update a user by ID
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        return adminService.updateUser(userId, user);
    }

    // Endpoint to get a user by ID
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return adminService.getUserById(userId);
    }

    // Endpoint to get all users
    @GetMapping
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    // Endpoint to delete a user by ID
    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        adminService.deleteUserById(userId);
    }
}
