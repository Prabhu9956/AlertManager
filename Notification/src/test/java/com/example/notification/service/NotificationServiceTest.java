package com.example.notification.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.notification.entity.Notification;
import com.example.notification.exception.NotificationNotFoundException;
import com.example.notification.repository.NotificationRepository;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@SpringBootTest
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateNotification() {
        Notification notification = new Notification(1L, 1L, "Test message", "email", "sent");

        when(notificationRepository.save(notification)).thenReturn(notification);

        Notification createdNotification = notificationService.createNotification(notification);
        
        assertNotNull(createdNotification);
        assertEquals(1L, createdNotification.getNotificationId());
        assertEquals(1L, createdNotification.getUserId());
        assertEquals("Test message", createdNotification.getMessage());
        assertEquals("email", createdNotification.getType());
        assertEquals("sent", createdNotification.getStatus());
    }

    @Test
    public void testUpdateNotification() {
        Long notificationId = 1L;
        Notification existingNotification = new Notification(notificationId, 1L, "Test message", "email", "sent");
        Notification updatedNotification = new Notification(notificationId, 1L, "Updated message", "SMS", "pending");

        when(notificationRepository.existsById(notificationId)).thenReturn(true);
        when(notificationRepository.save(updatedNotification)).thenReturn(updatedNotification);

        Notification result = notificationService.updateNotification(notificationId, updatedNotification);
        
        assertNotNull(result);
        assertEquals(notificationId, result.getNotificationId());
        assertEquals("Updated message", result.getMessage());
        assertEquals("SMS", result.getType());
        assertEquals("pending", result.getStatus());
    }

    @Test
    public void testUpdateNotification_NotFound() {
        Long notificationId = 1L;
        Notification updatedNotification = new Notification(notificationId, 1L, "Updated message", "SMS", "pending");

        when(notificationRepository.existsById(notificationId)).thenReturn(false);

        assertThrows(NotificationNotFoundException.class, () -> {
            notificationService.updateNotification(notificationId, updatedNotification);
        });
    }

    @Test
    public void testGetNotificationById() {
        Long notificationId = 1L;
        Notification mockNotification = new Notification(notificationId, 1L, "Test message", "email", "sent");

        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(mockNotification));

        Notification notification = notificationService.getNotificationById(notificationId);
        
        assertNotNull(notification);
        assertEquals(notificationId, notification.getNotificationId());
        assertEquals("Test message", notification.getMessage());
        assertEquals("email", notification.getType());
        assertEquals("sent", notification.getStatus());
    }

    @Test
    public void testGetNotificationById_NotFound() {
        Long notificationId = 1L;

        when(notificationRepository.findById(notificationId)).thenReturn(Optional.empty());

        assertThrows(NotificationNotFoundException.class, () -> {
            notificationService.getNotificationById(notificationId);
        });
    }

    @Test
    public void testGetAllNotifications() {
        List<Notification> mockNotifications = new ArrayList<>();
        mockNotifications.add(new Notification(1L, 1L, "Test message 1", "email", "sent"));
        mockNotifications.add(new Notification(2L, 1L, "Test message 2", "SMS", "pending"));

        when(notificationRepository.findAll()).thenReturn(mockNotifications);

        List<Notification> notifications = notificationService.getAllNotifications();
        
        assertNotNull(notifications);
        assertEquals(2, notifications.size());
    }

    @Test
    public void testDeleteNotificationById() {
        Long notificationId = 1L;

        when(notificationRepository.existsById(notificationId)).thenReturn(true);

        notificationService.deleteNotificationById(notificationId);

        verify(notificationRepository, times(1)).deleteById(notificationId);
    }

    @Test
    public void testDeleteNotificationById_NotFound() {
        Long notificationId = 1L;

        when(notificationRepository.existsById(notificationId)).thenReturn(false);

        assertThrows(NotificationNotFoundException.class, () -> {
            notificationService.deleteNotificationById(notificationId);
        });
    }
}
