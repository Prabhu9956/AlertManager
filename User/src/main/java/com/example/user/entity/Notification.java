package com.example.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Notification {
	@Id
    private Long notificationId;

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    @NotBlank(message = "Message is mandatory")
    private String message;

    @NotBlank(message = "Type is mandatory")
    private String type; // e.g., email, SMS, push

    @NotBlank(message = "Status is mandatory")
    private String status; // e.g., sent, pending
    
	public Long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", userId=" + userId + ", message=" + message
				+ ", type=" + type + ", status=" + status + "]";
	}
	public Notification(Long notificationId, Long userId, String message, String type, String status) {
		super();
		this.notificationId = notificationId;
		this.userId = userId;
		this.message = message;
		this.type = type;
		this.status = status;
	}
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}



