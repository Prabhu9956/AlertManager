package com.example.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.user.entity.Notification;
import com.example.user.entity.User;
import com.example.user.exception.UserNotFoundException;
import com.example.user.exception.NotificationNotFoundException;
import com.example.user.feignclientinterface.NotificationServiceFeignClient;
import com.example.user.feignclientinterface.UserServiceFeignClient;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserServiceFeignClient userServiceFeignClient;

    @Mock
    private NotificationServiceFeignClient notificationServiceFeignClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserByIdFeignClient() {
        Long userId = 1L;
        User mockUser = new User(userId, "Jane", "Doe", "jane.doe@example.com");

        when(userServiceFeignClient.getUserById(userId)).thenReturn(mockUser);

        User user = userService.getUserByIdFeignClient(userId);
        
        assertNotNull(user);
        assertEquals(userId, user.getUserId());
        assertEquals("Jane", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("jane.doe@example.com", user.getEmail());
    }

    @Test
    public void testGetUserByIdFeignClient_NotFound() {
        Long userId = 1L;

        when(userServiceFeignClient.getUserById(userId)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserByIdFeignClient(userId);
        });
    }

    @Test
    public void testCreateUserByFeignClient() {
        User user = new User(1L, "John", "Doe", "john.doe@example.com");

        when(userServiceFeignClient.createUser(user)).thenReturn(user);

        User createdUser = userService.createUserByFeignClient(user);
        
        assertNotNull(createdUser);
        assertEquals(1L, createdUser.getUserId());
        assertEquals("John", createdUser.getFirstName());
        assertEquals("Doe", createdUser.getLastName());
        assertEquals("john.doe@example.com", createdUser.getEmail());
    }

    @Test
    public void testUpdateUserByIdFeignClient() {
        Long userId = 1L;
        User existingUser = new User(userId, "John", "Doe", "john.doe@example.com");
        User updatedUser = new User(userId, "Johnny", "Doey", "johnny.doey@example.com");

        when(userServiceFeignClient.getUserById(userId)).thenReturn(existingUser);
        when(userServiceFeignClient.updateUser(userId, updatedUser)).thenReturn(updatedUser);

        User result = userService.updateUserByIdFeignClient(userId, updatedUser);
        
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals("Johnny", result.getFirstName());
        assertEquals("Doey", result.getLastName());
        assertEquals("johnny.doey@example.com", result.getEmail());
    }

    @Test
    public void testUpdateUserByIdFeignClient_NotFound() {
        Long userId = 1L;
        User updatedUser = new User(userId, "Johnny", "Doey", "johnny.doey@example.com");

        when(userServiceFeignClient.getUserById(userId)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUserByIdFeignClient(userId, updatedUser);
        });
    }

    @Test
    public void testGetNotificationByIdFeignClient() {
        Long notificationId = 1L;
        Notification mockNotification = new Notification(notificationId, 1L, "Test message", "email", "sent");

        when(notificationServiceFeignClient.getNotificationById(notificationId)).thenReturn(mockNotification);

        Notification notification = userService.getNotificationByIdFeignClient(notificationId);
        
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
            userService.getNotificationByIdFeignClient(notificationId);
        });
    }
}
