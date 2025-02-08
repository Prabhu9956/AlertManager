package com.example.notification.service;

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
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public Notification createNotification(@Valid Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(Long notificationId, @Valid Notification notification) {
        if (!notificationRepository.existsById(notificationId)) {
            throw new NotificationNotFoundException("Notification not found with id " + notificationId);
        }
        notification.setNotificationId(notificationId);
        return notificationRepository.save(notification);
    }

    public Notification getNotificationById(Long notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        if (!notification.isPresent()) {
            throw new NotificationNotFoundException("Notification not found with id " + notificationId);
        }
        return notification.get();
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public void deleteNotificationById(Long notificationId) {
        if (!notificationRepository.existsById(notificationId)) {
            throw new NotificationNotFoundException("Notification not found with id " + notificationId);
        }
        notificationRepository.deleteById(notificationId);
    }
}
