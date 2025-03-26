package com.example.notification.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.notification.entity.Notification;
import com.example.notification.exception.NotificationNotFoundException;
import com.example.notification.repository.NotificationRepository;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class NotificationServiceImpl {

    @Autowired
    private NotificationRepository notificationRepository;

    // Method to create a notification with validation
    public Notification createNotification(@Valid Notification notification) {
        return notificationRepository.save(notification);
    }

    // Method to update a notification by ID with validation
    public Notification updateNotification(Long notificationId, @Valid Notification notification) {
        if (!notificationRepository.existsById(notificationId)) {
            throw new NotificationNotFoundException("Notification not found with id " + notificationId);
        }
        notification.setNotificationId(notificationId);
        return notificationRepository.save(notification);
    }

    // Method to get a notification by ID
    public Notification getNotificationById(Long notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        if (!notification.isPresent()) {
            throw new NotificationNotFoundException("Notification not found with id " + notificationId);
        }
        return notification.get();
    }

    // Method to get all notifications
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Method to delete a notification by ID
    public void deleteNotificationById(Long notificationId) {
        if (!notificationRepository.existsById(notificationId)) {
            throw new NotificationNotFoundException("Notification not found with id " + notificationId);
        }
        notificationRepository.deleteById(notificationId);
    }

    // Method to get notifications by userId
    public List<Notification> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }
}
