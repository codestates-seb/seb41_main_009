package com.codestates.hobby.domain.post.controller;

import com.codestates.hobby.domain.post.service.PostCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{post-id}/comments")
public class PostCommentController {
    private final PostCommentService postCommentService;
    @PostMapping
    public ResponseEntity<?> post(@PathVariable("post-id") long postId,
                                  @RequestBody String content,
                                  @AuthenticationPrincipal Long memberId) {
        postCommentService.post(memberId, postId, content);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity<?> patch(
            @PathVariable("post-id") long postId,
            @PathVariable("comment-id") long commentId,
            @RequestBody String content,
            @AuthenticationPrincipal Long memberId
    ) {
        postCommentService.update(memberId, postId, commentId, content);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity<?> delete(
            @PathVariable("post-id") long postId,
            @PathVariable("comment-id") long commentId,
            @AuthenticationPrincipal Long memberId
    ) {
        postCommentService.delete(memberId, postId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@PathVariable("post-id") long postId,
                                    @RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "1") int size) {
        postCommentService.findAll(postId, page, size);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}