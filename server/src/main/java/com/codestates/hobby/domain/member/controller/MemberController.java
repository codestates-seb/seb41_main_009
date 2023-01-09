package com.codestates.hobby.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    @PostMapping
    public ResponseEntity postMember() {
        log.info("\n\n--회원 가입--\n");
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId) {
        log.info("\n\n--회원 정보 수정--\n");
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") long memberId) {
        log.info("\n\n--회원 탈퇴--\n");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") long memberId) {
        log.info("\n\n--해당 회원의 정보 조회--\n");
        return new ResponseEntity(HttpStatus.OK);
    }
}