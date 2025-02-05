package com.example.admin.feignclientinterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.admin.entity.User;

@FeignClient(name = "user-service", url = "http://localhost:8008")
public interface UserServiceFeignClient {
    @GetMapping("/users/{userId}")
    User getUserById(@PathVariable("userId") Long userId);

    @PostMapping("/users")
    User createUser(@RequestBody User user);
}
