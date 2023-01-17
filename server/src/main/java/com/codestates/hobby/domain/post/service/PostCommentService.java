package com.codestates.hobby.domain.post.service;

import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.service.MemberService;
import com.codestates.hobby.domain.post.dto.PostCommentDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.post.entity.PostComment;
import com.codestates.hobby.domain.post.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;
    private final PostService postService;
    private final MemberService memberService;

    @Transactional
    public PostComment post(PostCommentDto.Post postDto) {
        Member member = memberService.findMemberById(postDto.getMemberId());
        Post post = postService.findVerifiedPost(postDto.getPostId());

        return postCommentRepository.save(new PostComment(post.getContent(), member, post));
    }

    @Transactional
    public PostComment update(PostCommentDto.Patch patchDto) {
        PostComment findComment = findVerifiedComment(patchDto.getCommentId());
        if (!isMatchMember(findComment, patchDto.getMemberId()))  throw new RuntimeException("권한이 없습니다.");
        else findComment.update(patchDto.getContent());
        return postCommentRepository.save(findComment);
    }

    @Transactional
    public void delete(long memberId, long postId, long commentId) {
        PostComment findComment = findVerifiedComment(commentId);
        if (!isMatchMember(findComment, memberId))  throw new RuntimeException("권한이 없습니다.");
        if (postService.findVerifiedPost(postId) == null) throw new RuntimeException("포스트가 존재하지 않습니다.");
        postCommentRepository.delete(findComment);

    }

    @Transactional(readOnly = true)
    public long getCount(long postId) {
        return postCommentRepository.countAllByPostId(postId);
    }

    @Transactional(readOnly = true)
    public Page<PostComment> findAll(long postId, PageRequest pageRequest) {
        return postCommentRepository.findAllByPostIdOrderByIdDesc(postId,pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<PostComment> findAllByMember(long memberId, PageRequest pageRequest) {
        return postCommentRepository.findAllByMemberIdOrderByIdDesc(memberId,pageRequest);
    }

    public boolean isMatchMember(PostComment postComment, long memberId){
        return postComment.getMember().getId().equals(memberId);
    }

    private PostComment findVerifiedComment(long commentId) {
        Optional<PostComment> optionalComment = postCommentRepository.findById(commentId);
        PostComment findPostComment = optionalComment.orElseThrow(() -> new RuntimeException("data is null"));
        return findPostComment;
    }
}
