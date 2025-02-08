package com.example.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.user.entity.Notification;
import com.example.user.entity.User;
import com.example.user.exception.UserNotFoundException;
import com.example.user.exception.NotificationNotFoundException;
import com.example.user.feignclientinterface.NotificationServiceFeignClient;
import com.example.user.feignclientinterface.UserServiceFeignClient;

import jakarta.validation.Valid;

import java.util.Optional;
import java.util.List;

@Service
@Validated
public class UserService {

    @Autowired
    private UserServiceFeignClient userServiceFeignClient;

    @Autowired
    private NotificationServiceFeignClient notificationServiceFeignClient;

    public User getUserByIdFeignClient(Long userId) {
        User user = userServiceFeignClient.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found with id " + userId);
        }
        return user;
    }

    public User createUserByFeignClient(@Valid User user) {
        return userServiceFeignClient.createUser(user);
    }

    public User updateUserByIdFeignClient(Long userId, @Valid User user) {
        User existingUser = userServiceFeignClient.getUserById(userId);
        if (existingUser == null) {
            throw new UserNotFoundException("User not found with id " + userId);
        }
        return userServiceFeignClient.updateUser(userId, user);
    }

    public Notification getNotificationByIdFeignClient(Long notificationId) {
        Notification notification = notificationServiceFeignClient.getNotificationById(notificationId);
        if (notification == null) {
            throw new NotificationNotFoundException("Notification not found with id " + notificationId);
        }
        return notification;
    }
}
