package com.codestates.hobby.domain.auth.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException {
		response.setStatus(HttpStatus.OK.value());
		response.setContentType("application/json");
		response.getWriter().print(auth.getPrincipal());
		response.getWriter().close();
		log.info("\n\n--로그인 성공-- Member Id : {}", auth.getPrincipal());
	}
}
