package com.example.admin.service.user;

import com.example.admin.entity.User;
import com.example.admin.exception.UserNotFoundException;
import com.example.admin.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    // Method to create a user with validation
    public User createUser(@Valid User user) {
        return userRepository.save(user);
    }

    // Method to update a user by ID with validation
    public User updateUser(Long userId, @Valid User user) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id " + userId);
        }
        user.setUserId(userId);
        return userRepository.save(user);
    }

    // Method to get a user by ID
    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found with id " + userId);
        }
        return user.get();
    }

    // Method to get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Method to delete a user by ID
    public void deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with id " + userId);
        }
        userRepository.deleteById(userId);
    }
}
