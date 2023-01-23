package com.codestates.hobby.global.support.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("!mock")
@RequiredArgsConstructor
public class GoogleEmailService implements EmailService {
	private final JavaMailSender mailSender;

	@Async
	public void send(String to, String subject, String text) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setText(text, true);
			mimeMessageHelper.setSubject(subject);

			mailSender.send(mimeMessage);
			log.info("sent email (to: {}, subject: {})", to, subject);
		} catch (MessagingException e) {
			log.error("exception occured: {}", e.getMessage());
		}
	}
}
