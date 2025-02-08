package com.example.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.admin.entity.Notification;
import com.example.admin.entity.User;
import com.example.admin.exception.NotificationNotFoundException;
import com.example.admin.exception.UserNotFoundException;
import com.example.admin.feignclientinterface.NotificationServiceFeignClient;
import com.example.admin.repository.UserRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class AdminService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private NotificationServiceFeignClient notificationServiceFeignClient;
    
    public Notification getNotificationByIdFeignClient(Long notificationId) {
        Notification notification = notificationServiceFeignClient.getNotificationById(notificationId);
        if (notification == null) {
            throw new NotificationNotFoundException("Notification not found with id " + notificationId);
        }
        return notification;
    }

    public User createUser(@Valid User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, @Valid User user) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id " + userId);
        }
        user.setUserId(userId);
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found with id " + userId);
        }
        return user.get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id " + userId);
        }
        userRepository.deleteById(userId);
    }

    public Notification createNotificationFeignClient(@Valid Notification notification) {
        return notificationServiceFeignClient.createNotification(notification);
    }

    public void deleteNotificationByIdFeignClient(Long notificationId) {
        Notification notification = notificationServiceFeignClient.getNotificationById(notificationId);
        if (notification == null) {
            throw new NotificationNotFoundException("Notification not found with id " + notificationId);
        }
        notificationServiceFeignClient.deleteNotificationById(notificationId);
    }

    public List<Notification> createNotificationsFeignClient() {
        return notificationServiceFeignClient.getAllNotifications();
    }
}
