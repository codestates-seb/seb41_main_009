package com.codestates.hobby.domain.post.service;

import com.codestates.hobby.domain.post.dto.PostCommentDto;
import com.codestates.hobby.domain.post.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostCommentService {
    @Transactional
    public PostComment post(PostCommentDto.Post postDto) {
        return null;
    }

    @Transactional
    public PostComment update(PostCommentDto.Patch patchDto) {
        return null;
    }

    @Transactional
    public void delete(long memberId, long postId, long commentId) {

    }

    @Transactional(readOnly = true)
    public int getCount(long postId) {
        return 0;
    }

    @Transactional(readOnly = true)
    public Page<PostComment> findAll(long postId, PageRequest pageRequest) {
        return null;
    }

    @Transactional(readOnly = true)
    public Page<PostComment> findAllByMember(long memberId, PageRequest pageRequest) {
        return null;
    }
}
