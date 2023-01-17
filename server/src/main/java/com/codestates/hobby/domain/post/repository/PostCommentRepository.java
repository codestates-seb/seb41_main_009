package com.codestates.hobby.domain.post.repository;

import com.codestates.hobby.domain.post.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment,Long> {
    long countAllByPostId(long postId);

    Page<PostComment> findAllByPostIdOrderByIdDesc(long postId, Pageable pageable);

    Page<PostComment> findAllByMemberIdOrderByIdDesc(long memberId, Pageable pageable);

}
