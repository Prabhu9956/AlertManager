package com.example.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaNotificationApplication.class, args);
	}

}
