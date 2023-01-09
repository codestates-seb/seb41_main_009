package com.codestates.hobby.domain.notifcation.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class NotificationDto {
	@Getter
	@Setter
	@NoArgsConstructor
	public static class Response {
		private long id;
		private String endPoint;
		private String message;
		private Integer count;
		private LocalDateTime createdAt;
	}
}
