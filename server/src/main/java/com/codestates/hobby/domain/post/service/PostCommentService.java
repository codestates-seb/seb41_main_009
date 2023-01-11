package com.codestates.hobby.domain.post.service;

import com.codestates.hobby.domain.post.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostCommentService {
    @Transactional
    public void post(long memberId, long postId, String content) {
    }

    @Transactional
    public void update(long memberId, long postId, long commentId, String content) {

    }

    @Transactional
    public void delete(long memberId, long postId, long commentId) {

    }

    @Transactional(readOnly = true)
    public Page<PostComment> findAll(long postId, int page, int size) {
        return null;
    }
}
