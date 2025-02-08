package com.example.admin.feignclientinterface;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.admin.entity.Notification;

@FeignClient(name = "notification-service", url = "http://localhost:8010")
public interface NotificationServiceFeignClient {
    
    @PostMapping("/notifications")
    Notification createNotification(@RequestBody Notification notification);

    @GetMapping("/notifications/{notificationId}")
    Notification getNotificationById(@PathVariable Long notificationId);

    @DeleteMapping("/notifications/{notificationId}")
	void deleteNotificationById(@PathVariable Long notificationId);

    @GetMapping("/notifications")
	List<Notification> getAllNotifications();
}
