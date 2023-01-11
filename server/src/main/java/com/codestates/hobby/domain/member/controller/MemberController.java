package com.codestates.hobby.domain.member.controller;

import com.codestates.hobby.domain.member.dto.MemberDto;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.mapper.MemberMapper;
import com.codestates.hobby.domain.member.service.MemberService;
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
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity post(@RequestPart(value = "request") MemberDto.Post post,
                               @RequestPart(value = "profileImage") MultipartFile profileImage) {
        post.setProfileImage(profileImage);
        Member member = memberService.create(memberMapper.PostDtoToMember(post));

        log.info("\n\n--회원 가입--\n");
        return new ResponseEntity<>(member.getId(), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{member-id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity patch(@PathVariable("member-id") Long memberId,
                                @RequestPart(value = "request") MemberDto.Patch patch,
                                @RequestPart(value = "profileImage") MultipartFile profileImage) {

        patch.setProfileImage(profileImage);
        Member member = memberService.edit(memberId, memberMapper.PatchDtoToMember(patch));

        log.info("\n\n--회원 정보 수정--\n");
        return new ResponseEntity(memberId, HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity delete(@PathVariable("member-id") Long memberId) {
        memberService.delete(memberId);

        log.info("\n\n--회원 탈퇴--\n");
        return new ResponseEntity(memberId, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getAll(@PathVariable("member-id") Long memberId) {

        Member member = memberService.findAll(memberId);

        log.info("\n\n--해당 회원의 정보 조회--\n");
        return new ResponseEntity(memberMapper.MemberToResponseDto(member), HttpStatus.OK);
    }
}