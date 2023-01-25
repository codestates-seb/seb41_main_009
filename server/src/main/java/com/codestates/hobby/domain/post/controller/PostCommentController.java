package com.codestates.hobby.domain.post.controller;

import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.post.dto.PostCommentDto;
import com.codestates.hobby.domain.post.entity.PostComment;
import com.codestates.hobby.domain.post.mapper.PostCommentMapper;
import com.codestates.hobby.domain.post.service.PostCommentService;
import com.codestates.hobby.global.config.support.CustomPageRequest;
import com.codestates.hobby.global.dto.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{post-id}/comments")
public class PostCommentController {
    private final PostCommentService postCommentService;
    private final PostCommentMapper mapper;
    @PostMapping
    public ResponseEntity<?> post(@PathVariable("post-id") long postId,
                                  @RequestBody PostCommentDto.Post postDto,
                                  @SessionAttribute Member loginMember) {
        postDto.setProperties(postId, loginMember.getId());
        postCommentService.post(postDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity<?> patch(
            @PathVariable("post-id") long postId,
            @PathVariable("comment-id") long commentId,
            @RequestBody PostCommentDto.Patch patchDto,
            @SessionAttribute Member loginMember
    ) {
        patchDto.setProperties(loginMember.getId(), postId, commentId);
        postCommentService.update(patchDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity<?> delete(
            @PathVariable("post-id") long postId,
            @PathVariable("comment-id") long commentId,
            @SessionAttribute Member loginMember
    ) {
        postCommentService.delete(loginMember.getId(), postId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@PathVariable("post-id") long postId,
                                    @SessionAttribute Member loginMember,
                                    CustomPageRequest pageRequest) {
        Page<PostCommentDto.Response> postComments = postCommentService.findAll(postId,pageRequest.to()).map(mapper::postCommentToPostCommentResponse);
        return new ResponseEntity<>(new MultiResponseDto<>(postComments), HttpStatus.OK);
    }
}