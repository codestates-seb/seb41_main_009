package com.codestates.hobby.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostCommendController {
    // 필수 - 제목,내용,카테고리  선택 - 이미지,시리즈
    @PostMapping
    public ResponseEntity<?> post() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    // 선택 - 제목,내용,카테고리,이미지,시리즈
    @PatchMapping("/{post-id}")
    public ResponseEntity<?> patch(@PathVariable("post-id") long postId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity<?> delete(@PathVariable("post-id") long postId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
