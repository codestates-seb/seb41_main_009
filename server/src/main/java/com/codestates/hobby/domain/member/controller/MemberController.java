package com.codestates.hobby.domain.member.controller;

import com.codestates.hobby.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity postMember(@RequestPart MemberDto.Post post,
                                     @RequestPart MultipartFile profileImage) {
        log.info("\n\n--회원 가입--\n");
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{member-id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity patchMember(@PathVariable("member-id") long memberId,
                                      @RequestBody MemberDto.Patch patch,
                                      @RequestPart MultipartFile profileImage) {
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