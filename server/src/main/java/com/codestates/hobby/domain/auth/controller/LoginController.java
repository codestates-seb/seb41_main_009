package com.codestates.hobby.domain.auth.controller;

import com.codestates.hobby.domain.auth.Session.SessionConst;
import com.codestates.hobby.domain.auth.dto.LoginDto;
import com.codestates.hobby.domain.auth.service.LoginService;
import com.codestates.hobby.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto,
                      BindingResult bindingResult,
                      HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            return "에러";
        }

        Member loginMember = loginService.login(loginDto.getEmail(), loginDto.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "이메일 또는 비밀번호가 일치하지 않습니다");
            return "이메일 또는 비밀번호가 일치하지 않습니다";
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        log.info("\n\n--로그인 성공--\n");
        return "로그인 성공";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) session.invalidate(); //세션 제거

        log.info("\n\n--로그아웃 성공--\n");
        return "로그아웃 성공";
    }

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return "세션이 없습니다.";
        }
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={},value={}", name, session.getAttribute(name)));

        log.info("sessionId={}",session.getId());
        log.info("getMaxInactiveInterval={}",session.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(session.getCreationTime()));
        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));
        log.info("isNew={}", session.isNew());

        return "세션 출력";
    }
}
