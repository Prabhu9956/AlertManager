package com.example.admin.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.admin.entity.Notification;
import com.example.admin.entity.User;
import com.example.admin.exception.NotificationNotFoundException;
import com.example.admin.exception.UserNotFoundException;
import com.example.admin.feignclientinterface.NotificationServiceFeignClient;
import com.example.admin.repository.UserRepository;

@SpringBootTest
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private UserRepository userRepository;
    
    @Mock
    private NotificationServiceFeignClient notificationServiceFeignClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetNotificationByIdFeignClient() {
        Long notificationId = 1L;
        Notification mockNotification = new Notification(notificationId, 1L, "Test message", "email", "sent");

        when(notificationServiceFeignClient.getNotificationById(notificationId)).thenReturn(mockNotification);

        Notification notification = adminService.getNotificationByIdFeignClient(notificationId);
        
        assertNotNull(notification);
        assertEquals(notificationId, notification.getNotificationId());
        assertEquals("Test message", notification.getMessage());
        assertEquals("email", notification.getType());
        assertEquals("sent", notification.getStatus());
    }

    @Test
    public void testGetNotificationByIdFeignClient_NotFound() {
        Long notificationId = 1L;

        when(notificationServiceFeignClient.getNotificationById(notificationId)).thenReturn(null);

        assertThrows(NotificationNotFoundException.class, () -> {
            adminService.getNotificationByIdFeignClient(notificationId);
        });
    }

    @Test
    public void testCreateUser() {
        User user = new User(1L, "John", "Doe", "john.doe@example.com");

        when(userRepository.save(user)).thenReturn(user);

        User createdUser = adminService.createUser(user);
        
        assertNotNull(createdUser);
        assertEquals(1L, createdUser.getUserId());
        assertEquals("John", createdUser.getFirstName());
        assertEquals("Doe", createdUser.getLastName());
        assertEquals("john.doe@example.com", createdUser.getEmail());
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User mockUser = new User(userId, "John", "Doe", "john.doe@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        User user = adminService.getUserById(userId);
        
        assertNotNull(user);
        assertEquals(userId, user.getUserId());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    public void testGetUserById_NotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            adminService.getUserById(userId);
        });
    }
    
}
