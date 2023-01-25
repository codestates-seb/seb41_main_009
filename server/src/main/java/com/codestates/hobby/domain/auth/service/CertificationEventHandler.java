package com.codestates.hobby.domain.auth.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.codestates.hobby.domain.auth.event.CertificationCreatedEvent;
import com.codestates.hobby.global.support.mail.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CertificationEventHandler {
	private static final String TEMPLATE = "email-certification-template";
	private static final String SUBJECT = "Please certify your email";

	private final TemplateEngine templateEngine;
	private final EmailService emailService;

	@TransactionalEventListener
	public void handleCertificationCreatedEvent(CertificationCreatedEvent event) {
		Context context = new Context();
		context.setVariable("code", event.getCode());

		String html = templateEngine.process(TEMPLATE, context);
		emailService.send(event.getEmail(), SUBJECT, html);
	}
}
