package com.codestates.hobby.domain.post.service;

import com.codestates.hobby.domain.post.dto.PostDto;
import com.codestates.hobby.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
    @Transactional
    public Post post(Post post){
        /*
        1.verifyExist를 통한 검증
        2.포스트 저장
         */
        return post;
    }

    @Transactional
    public Post update(Post post){
        /*
        1.find를 통해 포스트 불러오기
        2.Optional를 통해 null값 확인
        3.변경사항 저장
         */
        return post;
    }
    @Transactional
    public void delete(long postId, long memberId){
        /*
        1.포스트 삭제
         */
    }

    @Transactional
    public Post findById(long postId) {
        /*
        1.아이디를 통한 검색
        2.조회수 증가
         */
        return null;
    }

    @Transactional(readOnly = true)
    public Page<Post> findByQuery(String query, int page, int size) {
        return null;
    }

    @Transactional(readOnly = true)
    public Page<Post> findAll(int page, int size, boolean isNewest) {
        return null;
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllByMember(long memberId, int page, int size, boolean isNewest) {
        return null;
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllByCategory(String categoryName, int page, int size, boolean isNewest) {
        return null;
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllBySeries(long seriesId, int page, int size, boolean isNewest) {
        return null;
    }
}
