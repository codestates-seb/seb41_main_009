package com.codestates.hobby.domain.post.repository;

import com.codestates.hobby.domain.post.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostCommentRepository extends JpaRepository<PostComment,Long> {
    long countAllByPostId(long postId);

    @EntityGraph(attributePaths = {"member"})
    Page<PostComment> findAllByPostIdOrderByIdDesc(@Param("postId")long postId, Pageable pageable);

    @EntityGraph(attributePaths = {"member"})
    Page<PostComment> findAllByMemberIdOrderByIdDesc(@Param("memberId") long memberId, Pageable pageable);

}
