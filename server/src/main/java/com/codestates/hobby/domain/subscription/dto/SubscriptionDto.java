package com.codestates.hobby.domain.subscription.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class SubscriptionDto {
	@Getter
	@Setter
	@NoArgsConstructor
	public static class Response {
		private long id;
		private LocalDateTime subscribedAt;

		// TODO: Writer(nickname, profileImageUrl)
	}
}
