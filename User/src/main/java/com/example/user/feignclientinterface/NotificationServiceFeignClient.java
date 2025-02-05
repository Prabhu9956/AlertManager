package com.example.user.feignclientinterface;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.user.entity.Notification;



@FeignClient(name = "notification-service", url = "http://localhost:8010")
public interface NotificationServiceFeignClient {
    @GetMapping("/notifications/{notificationId}")
    Notification getNotificationById(@PathVariable Long notificationId);
}

