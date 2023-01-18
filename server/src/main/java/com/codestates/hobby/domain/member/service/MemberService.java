package com.codestates.hobby.domain.member.service;

import com.codestates.hobby.global.exception.BusinessLogicException;
import com.codestates.hobby.global.exception.ExceptionCode;
import com.codestates.hobby.domain.member.dto.MemberDto;
import com.codestates.hobby.domain.member.repository.MemberRepository;
import com.codestates.hobby.domain.member.entity.Member;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member create(MemberDto.Post post) {
        //email 중복 확인
        verifyExistEmail(post.getEmail());
        //닉네임 중복 확인
        verifyExistNickname(post.getNickname());
        //비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(post.getPassword());

        return repository.save(new Member(post.getEmail(), post.getNickname(), encryptedPassword, post.getIntroduction(), false, post.getImgUrl()));
    }

    @Transactional
    public Member edit(MemberDto.Patch patch) {
        //멤버가 있는지 확인
        Member findMember = findMemberById(patch.getMemberId());
        verifyExistNickname(patch.getNickname());
        String encryptedPassword = passwordEncoder.encode(patch.getPassword());

        findMember.edit(patch.getNickname(), encryptedPassword, patch.getIntroduction(), patch.getImgUrl());

        return findMember;
    }

    @Transactional
    public void delete(long memberId) {
        //멤버가 있는지 확인
        Member findMember = findMemberById(memberId);
        findMember.setMemberStatus(Member.MemberStatus.MEMBER_QUIT);
    }

    @Transactional(readOnly = true)
    public Member findAll(long memberId) {
        return findMemberById(memberId);
    }

    @Transactional(readOnly = true)
    private void verifyExistEmail(String email) {
        Optional<Member> member = repository.findByEmail(email);
        if(member.isPresent()) throw new BusinessLogicException(ExceptionCode.EXISTS_EMAIL);
    }

    @Transactional(readOnly = true)
    private void verifyExistNickname(String nickname) {
        Optional<Member> member = repository.findByNickname(nickname);
        if(member.isPresent()) throw new BusinessLogicException(ExceptionCode.EXISTS_NICKNAME);
    }

    @Transactional(readOnly = true)
    public Member findMemberById(long memberId) {
        Optional<Member> optionalMember = repository.findById(memberId);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_MEMBER));
        if(findMember.getMemberStatus().equals(Member.MemberStatus.MEMBER_QUIT))
            throw new BusinessLogicException(ExceptionCode.WITHDRAWAL_MEMBER);
        return findMember;
    }

    @Transactional(readOnly = true)
    public Member findMemberByEmail(String email) {
        Optional<Member> optionalMember = repository.findByEmail(email);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_MEMBER));
        if(findMember.getMemberStatus().equals(Member.MemberStatus.MEMBER_QUIT))
            throw new BusinessLogicException(ExceptionCode.WITHDRAWAL_MEMBER);
        return findMember;
    }
}
