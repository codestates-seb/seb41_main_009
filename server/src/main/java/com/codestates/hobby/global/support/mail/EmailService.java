package com.codestates.hobby.global.support.mail;

public interface EmailService {
	void send(String to, String subject, String text);
}
