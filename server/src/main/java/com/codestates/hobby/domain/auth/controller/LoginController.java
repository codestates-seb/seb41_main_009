package com.codestates.hobby.domain.auth.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codestates.hobby.domain.auth.dto.CertificationPatchRequest;
import com.codestates.hobby.domain.auth.service.CertificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
	private final CertificationService certificationService;

	@PostMapping("/certifications")
	public ResponseEntity<?> attempt(@RequestBody @Email String email) {
		certificationService.attempt(email);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PatchMapping("/certifications")
	public ResponseEntity<?> certify(@RequestBody @Valid CertificationPatchRequest request) {
		certificationService.certify(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/logout")
	public ResponseEntity logout(HttpSession session) {
		if (session != null)
			session.invalidate(); //세션 제거

		log.info("\n\n--로그아웃 성공--\n");
		return new ResponseEntity("로그아웃 성공", HttpStatus.OK);
	}

	@GetMapping("/session-info")
	public String sessionInfo(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "세션이 없습니다.";
		}
		session.getAttributeNames().asIterator()
			.forEachRemaining(name -> log.info("session name={},value={}", name, session.getAttribute(name)));

		log.info("sessionId={}", session.getId());
		log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
		log.info("creationTime={}", new Date(session.getCreationTime()));
		log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));
		log.info("isNew={}", session.isNew());

		return "세션 출력";
	}
}
