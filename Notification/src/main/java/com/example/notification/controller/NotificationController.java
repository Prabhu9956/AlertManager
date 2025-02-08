package com.example.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.notification.entity.Notification;
import com.example.notification.service.NotificationService;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @PutMapping("/{notificationId}")
    public Notification updateNotification(@PathVariable Long notificationId, @RequestBody Notification notification) {
        return notificationService.updateNotification(notificationId, notification);
    }

    @GetMapping("/{notificationId}")
    public Notification getNotificationById(@PathVariable Long notificationId) {
        return notificationService.getNotificationById(notificationId);
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }
    
    @DeleteMapping("/{notificationId}")
    public void deleteNotificationById(@PathVariable Long notificationId) {
    	notificationService.deleteNotificationById(notificationId);
    }
}
