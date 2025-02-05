package com.example.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.entity.Notification;
import com.example.user.entity.User;
import com.example.user.feignclientinterface.NotificationServiceFeignClient;
import com.example.user.repository.UserRepository;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private NotificationServiceFeignClient notificationServiceFeignClient;
    
    public Notification getNotificationByIdFeignClient(Long notificationId) {
        return notificationServiceFeignClient.getNotificationById(notificationId);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User user) {
        if (userRepository.existsById(userId)) {
            user.setUserId(userId);
            return userRepository.save(user);
        }
        return null;
    }

    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}