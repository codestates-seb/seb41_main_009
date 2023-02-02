package com.codestates.hobby.domain.member.repository;

import com.codestates.hobby.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(@Param("email") String email);
    Optional<Member> findByNickname(String nickname);
    boolean existsByEmail(String email);
}
