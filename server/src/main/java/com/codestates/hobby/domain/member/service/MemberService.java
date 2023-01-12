package com.codestates.hobby.domain.member.service;

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

    public Member create(Member member) {
        //email 중복 확인
        verifyExistEmail(member.getEmail());

        //닉네임 중복 확인
        verifyExistNickname(member.getNickname());

        //비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());

        //(String email, String nickname, String password, boolean isOauth2) {
        member = new Member(member.getEmail(), member.getNickname(), member.getPassword(), member.getIntroduction(), false);

        return repository.save(member);
    }

    public Member edit(long memberId, Member member) {
        //멤버가 있는지 확인
        Member findMember = findMemberById(memberId);
        //수정을 요청한 멤버와 로그인 중인 멤버가 일치하는지 확인
        if(findMember.getId() != loginMember().getId()) {
            throw new RuntimeException("Unauthorized");
        }

        //변경하려는 nickname 중복 확인
        //verifiedNickname = repository.findByNickname(member.getNickname())

        return member;
    }

    public void delete(long memberId) {
        //멤버가 있는지 확인
        Member findMember = findMemberById(memberId);
        //삭제를 요청한 멤버와 로그인 중인 멤버가 일치하는지 확인
        if(findMember.getId() != loginMember().getId()) {
            throw new RuntimeException("Unauthorized");
        }
    }

    public Member findAll(long memberId) {
        //멤버가 있는지 확인
        Member findMember = findMemberById(memberId);
        //정보 조회를 요청한 멤버와 로그인 중인 멤버가 일치하는지 확인
        if(findMember.getId() != loginMember().getId()) {
            throw new RuntimeException("Unauthorized");
        }

        return null;
    }

    private void verifyExistEmail(String email) {
        Optional<Member> member = repository.findByEmail(email);
        if(member.isPresent()) throw new RuntimeException("Email Exists");
    }

    private void verifyExistNickname(String nickname) {
        Optional<Member> member = repository.findByNickname(nickname);
        if(member.isPresent()) throw new RuntimeException("Nickname Exists");
    }

    //findVerifiedById 에서 수정되었습니다.
    public Member findMemberById(long memberId) {
        Optional<Member> optionalMember = repository.findById(memberId);
        Member findMember = optionalMember.orElseThrow(() -> new RuntimeException("Member Not Found"));
        //해당 id로 가입된 멤버가 있는지 확인

        //repository.findById(memberId)
        //있다면 해당 member를 반환하고
        //없다면 MEMBER_NOT_FOUND
        return null;
    }

    public Member findMemberByEmail(String email) {
        //해당 email로 가입된 멤버가 있는지 확인
        //repository.findByEmail(email)
        //있다면 해당 member를 반환하고
        //없다면 MEMBER_NOT_FOUND
        return null;
    }

    //로그인 중인 유저 가져오기
    public Member loginMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getName() == null || authentication.getName().equals("anonymousUser"))
            throw new RuntimeException("Unauthorized");

        //로그인 중인 멤버가 존재하는지 확인 후 존재하면 해당 member를 리턴
        //Member member = repository.findByEmail(authentication.getName())

        return null;
    }
}
