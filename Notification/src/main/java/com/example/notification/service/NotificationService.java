package com.example.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.notification.entity.Notification;
import com.example.notification.repository.NotificationRepository;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(Long notificationId, Notification notification) {
        if (notificationRepository.existsById(notificationId)) {
            notification.setNotificationId(notificationId);
            return notificationRepository.save(notification);
        }
        return null;
    }

    public Notification getNotificationById(Long notificationId) {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        return notification.orElse(null);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}
