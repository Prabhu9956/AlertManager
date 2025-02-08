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
    
    @GetMapping("/feign/notification/{notificationId}")
    public Notification getNotificationByIdFeignClient(@PathVariable Long userId) {
        return adminService.getNotificationByIdFeignClient(userId);
    }
    @PostMapping("/feign/notification")
    public Notification createNotificationFeignClient(@RequestBody Notification notification) {
    	return adminService.createNotificationFeignClient(notification);
    }
    
    @GetMapping("/feign/notifications")
    public List<Notification> getAllNotificationsFeignClient(){
    	return adminService.createNotificationsFeignClient();
    }
    
    @DeleteMapping("/feign/notification/{notificationId}")
    public void deleteNotificationByIdFeignClient(@PathVariable Long notificationId) {
    	adminService.deleteNotificationByIdFeignClient(notificationId);
    }
    
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return adminService.createUser(user);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        return adminService.updateUser(userId, user);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return adminService.getUserById(userId);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        adminService.deleteUserById(userId);
    }
    
}