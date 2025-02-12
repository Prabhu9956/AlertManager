package com.example.admin.service;

import com.example.admin.entity.Notification;
import com.example.admin.entity.User;
import com.example.admin.service.user.UserServiceImpl;
import com.example.admin.service.notification.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class AdminService {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;

    // Method to create a user
    public User createUser(User user) {
        return userServiceImpl.createUser(user);
    }

    // Method to update a user by ID
    public User updateUser(Long userId, User user) {
        return userServiceImpl.updateUser(userId, user);
    }

    // Method to get a user by ID
    public User getUserById(Long userId) {
        return userServiceImpl.getUserById(userId);
    }

    // Method to get all users
    public List<User> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    // Method to delete a user by ID
    public void deleteUserById(Long userId) {
        userServiceImpl.deleteUserById(userId);
    }

    // Method to get a notification by ID using Feign Client
    public Notification getNotificationByIdFeignClient(Long notificationId) {
        return notificationServiceImpl.getNotificationByIdFeignClient(notificationId);
    }

    // Method to create a notification using Feign Client
    public Notification createNotificationFeignClient(Notification notification) {
        return notificationServiceImpl.createNotificationFeignClient(notification);
    }

    // Method to delete a notification by ID using Feign Client
    public void deleteNotificationByIdFeignClient(Long notificationId) {
        notificationServiceImpl.deleteNotificationByIdFeignClient(notificationId);
    }

    // Method to get all notifications using Feign Client
    public List<Notification> getAllNotificationsFeignClient() {
        return notificationServiceImpl.getAllNotificationsFeignClient();
    }
}
