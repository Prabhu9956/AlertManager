package com.example.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.user.entity.Notification;
import com.example.user.entity.User;
import com.example.user.service.impl.UserServiceImpl;
import com.example.user.service.impl.NotificationServiceImpl;

import jakarta.validation.Valid;
import java.util.List;

@Service
@Validated
public class UserService {
    

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;

    

    // Method to get a notification by ID using Feign Client
    public Notification getNotificationByIdFeignClient(Long notificationId) {
        return notificationServiceImpl.getNotificationByIdFeignClient(notificationId);
    }

	public List<Notification> getNotificationsByUserIdFeignClient(Long userId) {
		return notificationServiceImpl.getNotificationByUserIdFeignClient(userId);
	}
}
