package com.codestates.hobby.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostCommendController {
    // 필수 - 제목,내용,카테고리  선택 - 이미지,시리즈
    @PostMapping
    public ResponseEntity<?> post() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping(value = "/{post-id}/files",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(MultipartFile file){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    // 이미지 Patch 시 delete는 url String 삭제로, 새 이미지 upload는 upload 메서드를 통해 진행.
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
