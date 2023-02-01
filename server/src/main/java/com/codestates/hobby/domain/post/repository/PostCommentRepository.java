package com.codestates.hobby.domain.post.repository;

import com.codestates.hobby.domain.post.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostCommentRepository extends JpaRepository<PostComment,Long> {
    long countAllByPostId(long postId);

    @Query(value = "select c from PostComment c join fetch c.member m join fetch m.image join fetch m.roles where c.post.id = :postId order by c.id desc",
            countQuery = "select c from PostComment c where c.post.id = :postId")
    Page<PostComment> findAllByPostIdOrderByIdDesc(@Param("postId")long postId, Pageable pageable);

    @Query(value = "select c from PostComment c join fetch c.member m join fetch m.image join fetch m.roles where c.member.id = :memberId order by c.id desc",
            countQuery = "select c from PostComment c where c.member.id = :memberId")
    Page<PostComment> findAllByMemberIdOrderByIdDesc(@Param("memberId") long memberId, Pageable pageable);

}
