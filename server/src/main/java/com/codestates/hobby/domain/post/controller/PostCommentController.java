package com.codestates.hobby.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostCommentController {
    // 필수 - 내용
    @PostMapping("/{post-id}/comments")
    public ResponseEntity<?> post(@PathVariable("post-id") long postId) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    // 선택 - 내용
    @PatchMapping("/{post-id}/comments/{comment-id}")
    public ResponseEntity<?> patch(
            @PathVariable("post-id") long postId,
            @PathVariable("comment-id") long commentId
    ) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{post-id}/comments/{comment-id}")
    public ResponseEntity<?> delete(
            @PathVariable("post-id") long postId,
            @PathVariable("comment-id") long commentId
    ) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{post-id}/comments")
    public ResponseEntity<?> getAll(@PathVariable("post-id") long postId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}