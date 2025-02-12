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

    //End point to create a notification
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    // End point to update a notification by ID
    @PutMapping("/{notificationId}")
    public Notification updateNotification(@PathVariable Long notificationId, @RequestBody Notification notification) {
        return notificationService.updateNotification(notificationId, notification);
    }

    // End point to get a notification by ID
    @GetMapping("/{notificationId}")
    public Notification getNotificationById(@PathVariable Long notificationId) {
        return notificationService.getNotificationById(notificationId);
    }

    // End point to get all notifications
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }
    
    // End point to delete a notification by ID
    @DeleteMapping("/{notificationId}")
    public void deleteNotificationById(@PathVariable Long notificationId) {
        notificationService.deleteNotificationById(notificationId);
    }
}
