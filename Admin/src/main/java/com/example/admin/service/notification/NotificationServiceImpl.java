package com.example.admin.service.notification;

import com.example.admin.entity.Notification;
import com.example.admin.exception.NotificationNotFoundException;
import com.example.admin.feignclientinterface.NotificationServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

import java.util.List;

@Service
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

    // Method to create a notification using Feign Client with validation
    public Notification createNotificationFeignClient(@Valid Notification notification) {
        return notificationServiceFeignClient.createNotification(notification);
    }

    // Method to delete a notification by ID using Feign Client
    public void deleteNotificationByIdFeignClient(Long notificationId) {
        Notification notification = notificationServiceFeignClient.getNotificationById(notificationId);
        if (notification == null) {
            throw new NotificationNotFoundException("Notification not found with id " + notificationId);
        }
        notificationServiceFeignClient.deleteNotificationById(notificationId);
    }

    // Method to get all notifications using Feign Client
    public List<Notification> getAllNotificationsFeignClient() {
        return notificationServiceFeignClient.getAllNotifications();
    }
}
