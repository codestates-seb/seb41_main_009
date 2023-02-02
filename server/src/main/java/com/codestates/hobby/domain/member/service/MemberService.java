package com.codestates.hobby.domain.member.service;

import com.codestates.hobby.domain.auth.service.CertificationService;
import com.codestates.hobby.domain.auth.utils.CustomAuthorityUtils;
import com.codestates.hobby.domain.member.dto.MemberDto;
import com.codestates.hobby.domain.member.repository.MemberRepository;
import com.codestates.hobby.domain.member.entity.Member;

import com.codestates.hobby.global.exception.BusinessLogicException;
import com.codestates.hobby.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final CertificationService certificationService;
    private final CustomAuthorityUtils authorityUtils;

    @Transactional
    public Member create(MemberDto.Post post) {
        certificationService.verifyEmail(post.getEmail());

        verifyExistEmail(post.getEmail());
        verifyExistNickname(post.getNickname());
        String encryptedPassword = passwordEncoder.encode(post.getPassword());
        List<String> roles = authorityUtils.createRoles(post.getEmail());

        return repository.save(new Member(post.getEmail(), post.getNickname(), encryptedPassword,false, roles));
    }

    @Transactional
    public Member edit(MemberDto.Patch patch, long loginId) {
        Member findMember = findMemberById(patch.getMemberId());
        if(patch.getMemberId() != loginId) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);

        verifyExistNickname(patch.getNickname());

        findMember.edit(patch.getNickname(), patch.getIntroduction(), patch.getProfileUrl());

        return findMember;
    }

    @Transactional
    public void delete(long memberId) {
        Member findMember = findMemberById(memberId);
        findMember.setStatus(Member.MemberStatus.MEMBER_QUIT);
    }

    @Transactional(readOnly = true)
    public Member find(long memberId) {
        return findMemberById(memberId);
    }

    @Transactional(readOnly = true)
    public Page<Member> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    protected void verifyExistEmail(String email) {
        Optional<Member> member = repository.findByEmail(email);
        if(member.isPresent()) throw new BusinessLogicException(ExceptionCode.EXISTS_EMAIL);
    }

    @Transactional(readOnly = true)
    protected void verifyExistNickname(String nickname) {
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
