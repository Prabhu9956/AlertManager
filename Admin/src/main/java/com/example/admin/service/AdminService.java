package com.example.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.admin.entity.Notification;
import com.example.admin.entity.User;
import com.example.admin.feignclientinterface.NotificationServiceFeignClient;
import com.example.admin.feignclientinterface.UserServiceFeignClient;
import com.example.admin.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserServiceFeignClient userServiceFeignClient;
    
    @Autowired
    private NotificationServiceFeignClient notificationServiceFeignClient;

    public User getUserByIdFeignClient(Long userId) {
        return userServiceFeignClient.getUserById(userId);
    }
    
    public Notification getNotificationByIdFeignClient(Long notificationId) {
        return notificationServiceFeignClient.getNotificationById(notificationId);
    }
    

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User  updateUser(Long userId, User user) {
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

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

	public Notification createNotificationFeignClient(Notification notification) {
		return notificationServiceFeignClient.createNotification(notification);
	}

	public User createUserByFeignClient(User user) {
		return userServiceFeignClient.createUser(user);
	}
}
