package com.codestates.hobby.global.support.mail;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("local")
@RequiredArgsConstructor
public class MockEmailService implements EmailService {
	public void send(String to, String subject, String text) {
		log.info("Sent email (to: {}, subject: {}, text: {})", to, subject, text);
	}
}
