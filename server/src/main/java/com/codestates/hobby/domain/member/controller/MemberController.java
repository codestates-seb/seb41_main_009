package com.codestates.hobby.domain.member.controller;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.mapper.MemberMapper;
import com.codestates.hobby.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService service;
    private final MemberMapper mapper;

    @PostMapping
    public ResponseEntity post(@RequestBody MemberDto.Post post,
                               @AuthenticationPrincipal Long authId) {
        post.setMemberId(authId);
        Member member = service.create(post);

        log.info("\n\n--회원 가입--\n");
        return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patch(@PathVariable("member-id") long memberId,
                                @AuthenticationPrincipal Long authId,
                                @RequestBody MemberDto.Patch patch) {
        if(memberId != authId) throw new RuntimeException("Unauthorized");
        patch.setMemberId(memberId);

        Member member = service.edit(patch);

        log.info("\n\n--회원 정보 수정--\n");
        return new ResponseEntity(member.getId(), HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity delete(@PathVariable("member-id") long memberId,
                                 @AuthenticationPrincipal Long authId) {
        if(memberId != authId) throw new RuntimeException("Unauthorized");

        service.delete(memberId);

        log.info("\n\n--회원 탈퇴--\n");
        return new ResponseEntity(memberId, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getAll(@PathVariable("member-id") long memberId,
                                 @AuthenticationPrincipal Long authId) {
        if(memberId != authId) throw new RuntimeException("Unauthorized");

        Member member = service.findAll(memberId);

        log.info("\n\n--해당 회원의 정보 조회--\n");
        return new ResponseEntity(mapper.MemberToResponseDto(member), HttpStatus.OK);
    }
}