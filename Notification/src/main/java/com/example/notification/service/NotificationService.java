package com.example.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.notification.entity.Notification;
import com.example.notification.service.impl.NotificationServiceImpl;

import jakarta.validation.Valid;
import java.util.List;

@Service
@Validated
public class NotificationService {

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;

    // Method to create a notification with validation
    public Notification createNotification(@Valid Notification notification) {
        return notificationServiceImpl.createNotification(notification);
    }

    // Method to update a notification by ID with validation
    public Notification updateNotification(Long notificationId, @Valid Notification notification) {
        return notificationServiceImpl.updateNotification(notificationId, notification);
    }

    // Method to get a notification by ID
    public Notification getNotificationById(Long notificationId) {
        return notificationServiceImpl.getNotificationById(notificationId);
    }

    // Method to get all notifications
    public List<Notification> getAllNotifications() {
        return notificationServiceImpl.getAllNotifications();
    }

    // Method to delete a notification by ID
    public void deleteNotificationById(Long notificationId) {
        notificationServiceImpl.deleteNotificationById(notificationId);
    }

    // Method to get notifications by userId
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationServiceImpl.getNotificationsByUserId(userId);
    }
}
