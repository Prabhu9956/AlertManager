package com.example.user.feignclientinterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.user.entity.User;

@FeignClient(name = "admin-service", url = "http://localhost:8009")
public interface UserServiceFeignClient {
    @GetMapping("/admin/{userId}")
    User getUserById(@PathVariable("userId") Long userId);

    @PostMapping("/admin/users")
    User createUser(@RequestBody User user);
    
    @PutMapping("/admin/{userId}")
    User updateUser(@PathVariable Long userId, @RequestBody User user);
}
