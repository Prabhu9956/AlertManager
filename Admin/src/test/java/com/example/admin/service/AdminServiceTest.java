package com.example.admin.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.admin.entity.Notification;
import com.example.admin.entity.User;
import com.example.admin.exception.NotificationNotFoundException;
import com.example.admin.exception.UserNotFoundException;
import com.example.admin.feignclientinterface.NotificationServiceFeignClient;
import com.example.admin.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AdminServiceTest {

    @Mock
    private UserRepository userRepository;
    
    @Mock
    private NotificationServiceFeignClient notificationServiceFeignClient;

    @InjectMocks
    private AdminService adminService;

    private User user;
    private Notification notification;

    @BeforeEach
    public void setUp() {
        user = new User(1L, "John", "Doe", "john.doe@example.com");
        notification = new Notification(1L, 1L, "Test Message", "email", "sent");
    }

    @Test
    public void testGetNotificationByIdFeignClient() {
        when(notificationServiceFeignClient.getNotificationById(anyLong())).thenReturn(notification);

        Notification result = adminService.getNotificationByIdFeignClient(1L);
        assertNotNull(result);
        assertEquals("Test Message", result.getMessage());
    }

    @Test
    public void testGetNotificationByIdFeignClient_NotFound() {
        when(notificationServiceFeignClient.getNotificationById(anyLong())).thenReturn(null);

        assertThrows(NotificationNotFoundException.class, () -> {
            adminService.getNotificationByIdFeignClient(1L);
        });
    }

    @Test
    public void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = adminService.createUser(user);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.existsById(anyLong())).thenReturn(true);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = adminService.updateUser(1L, user);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    public void testUpdateUser_NotFound() {
        when(userRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> {
            adminService.updateUser(1L, user);
        });
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User result = adminService.getUserById(1L);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    public void testGetUserById_NotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            adminService.getUserById(1L);
        });
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<User> result = adminService.getAllUsers();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testDeleteUserById() {
        when(userRepository.existsById(anyLong())).thenReturn(true);

        adminService.deleteUserById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteUserById_NotFound() {
        when(userRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> {
            adminService.deleteUserById(1L);
        });
    }

    @Test
    public void testCreateNotificationFeignClient() {
        when(notificationServiceFeignClient.createNotification(any(Notification.class))).thenReturn(notification);

        Notification result = adminService.createNotificationFeignClient(notification);
        assertNotNull(result);
        assertEquals("Test Message", result.getMessage());
    }

    @Test
    public void testDeleteNotificationByIdFeignClient() {
        when(notificationServiceFeignClient.getNotificationById(anyLong())).thenReturn(notification);

        adminService.deleteNotificationByIdFeignClient(1L);
        verify(notificationServiceFeignClient, times(1)).deleteNotificationById(1L);
    }

    @Test
    public void testDeleteNotificationByIdFeignClient_NotFound() {
        when(notificationServiceFeignClient.getNotificationById(anyLong())).thenReturn(null);

        assertThrows(NotificationNotFoundException.class, () -> {
            adminService.deleteNotificationByIdFeignClient(1L);
        });
    }

    @Test
    public void testCreateNotificationsFeignClient() {
        when(notificationServiceFeignClient.getAllNotifications()).thenReturn(Arrays.asList(notification));

        List<Notification> result = adminService.createNotificationsFeignClient();
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
