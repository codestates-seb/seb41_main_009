package com.codestates.hobby.domain.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Slf4j
@RestController
public class SessionController {

    @Autowired
    HttpSession httpSession;

    @PostMapping("/login2")
    public ResponseEntity<?> login(HttpSession session){
        UUID uid = Optional.ofNullable(UUID.class.cast(session.getAttribute("uid")))
                .orElse(UUID.randomUUID());
        session.setAttribute("uid", uid);
        return new ResponseEntity<>(uid, HttpStatus.OK);
    }

    @GetMapping("/logout2")
    public ResponseEntity<?> logout(HttpSession session){
        session.invalidate();
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }
}
