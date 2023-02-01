package com.codestates.hobby.domain.member.repository;

import com.codestates.hobby.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m join fetch m.roles where m.email = :email")
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
    boolean existsByEmail(String email);
}
