package com.example.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.user.entity.Notification;
import com.example.user.exception.NotificationNotFoundException;
import com.example.user.feignclientinterface.NotificationServiceFeignClient;
import jakarta.validation.Valid;

import java.util.List;

@Service
@Validated
public class NotificationServiceImpl {
    
    @Autowired
    private NotificationServiceFeignClient notificationServiceFeignClient;

    // Method to get a notification by ID using Feign Client
    public Notification getNotificationByIdFeignClient(Long notificationId) {
        Notification notification = notificationServiceFeignClient.getNotificationById(notificationId);
        if (notification == null) {
            throw new NotificationNotFoundException("Notification not found with id " + notificationId);
        }
        return notification;
    }
}
