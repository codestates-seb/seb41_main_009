package com.codestates.hobby.domain.member.service;

import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.fileInfo.service.FileInfoService;
import com.codestates.hobby.domain.member.dto.MemberDto;
import com.codestates.hobby.domain.member.repository.MemberRepository;
import com.codestates.hobby.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final FileInfoService fileInfoService;

    public Member create(MemberDto.Post post) {
        //email 중복 확인
        verifyExistEmail(post.getEmail());
        //닉네임 중복 확인
        verifyExistNickname(post.getNickname());
        //비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(post.getPassword());

        FileInfo fileInfo = fileInfoService.saveByUrl(post.getImgUrl());

        return repository.save(new Member(post.getEmail(), post.getNickname(), encryptedPassword, post.getIntroduction(), false, fileInfo));
    }

    public Member edit(long authId, MemberDto.Patch patch) {
        //멤버가 있는지 확인
        Member findMember = findMemberById(patch.getMemberId());
        //수정을 요청한 멤버와 로그인 중인 멤버가 일치하는지 확인
        if(findMember.getId() != authId) {
            throw new RuntimeException("Unauthorized");
        }
        verifyExistNickname(patch.getNickname());

        Optional.ofNullable(patch.getNickname()).ifPresent(nick->patch.setNickname(nick));
        Optional.ofNullable(patch.getPassword()).ifPresent(pw -> patch.setPassword(pw));
        Optional.ofNullable(patch.getIntroduction()).ifPresent(intro->patch.setIntroduction(intro));
        Optional.ofNullable(patch.getImgUrl()).ifPresent(url-> patch.setImgUrl(url));

        String encryptedPassword = passwordEncoder.encode(patch.getPassword());

        FileInfo fileInfo = fileInfoService.saveByUrl(patch.getImgUrl());

        findMember.edit(patch.getNickname(), encryptedPassword, patch.getIntroduction(), fileInfo);

        return findMember;
    }

    public void delete(long memberId, long authId) {
        //멤버가 있는지 확인
        Member findMember = findMemberById(memberId);
        //삭제를 요청한 멤버와 로그인 중인 멤버가 일치하는지 확인
        if(findMember.getId() != authId) {
            throw new RuntimeException("Unauthorized");
        }
        findMember.setMemberStatus(Member.MemberStatus.MEMBER_QUIT);
    }

    public Member findAll(long memberId, long authId) {
        //멤버가 있는지 확인
        Member findMember = findMemberById(memberId);
        //정보 조회를 요청한 멤버와 로그인 중인 멤버가 일치하는지 확인
        if(findMember.getId() != authId) {
            throw new RuntimeException("Unauthorized");
        }
        return findMember;
    }

    private void verifyExistEmail(String email) {
        Optional<Member> member = repository.findByEmail(email);
        if(member.isPresent()) throw new RuntimeException("Email Exists");
    }

    private void verifyExistNickname(String nickname) {
        Optional<Member> member = repository.findByNickname(nickname);
        if(member.isPresent()) throw new RuntimeException("Nickname Exists");
    }

    public Member findMemberById(long memberId) {
        Optional<Member> optionalMember = repository.findById(memberId);
        Member findMember = optionalMember.orElseThrow(() -> new RuntimeException("Not Found Member"));
        if(!findMember.getMemberStatus().equals(Member.MemberStatus.MEMBER_ACTIVE))
            throw new RuntimeException("탈퇴한 멤버입니다.");
        return findMember;
    }

    public Member findMemberByEmail(String email) {
        Optional<Member> optionalMember = repository.findByEmail(email);
        Member findMember = optionalMember.orElseThrow(() -> new RuntimeException("Not Found Member"));
        if(!findMember.getMemberStatus().equals(Member.MemberStatus.MEMBER_ACTIVE))
            throw new RuntimeException("탈퇴한 멤버입니다.");
        return findMember;
    }
}
