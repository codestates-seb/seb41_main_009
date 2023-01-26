package com.codestates.hobby.domain.auth.event;

import lombok.Getter;

@Getter
public class CertificationCreatedEvent {
	private final String email;
	private final int code;

	public CertificationCreatedEvent(String email, int code) {
		this.email = email;
		this.code = code;
	}
}
