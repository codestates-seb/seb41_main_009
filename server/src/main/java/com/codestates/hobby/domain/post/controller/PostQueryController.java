package com.codestates.hobby.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostQueryController {
    @GetMapping("/{post-id}")
    public ResponseEntity<?> get(@PathVariable("post-id") long postId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam int page,
                                    @RequestParam int size) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<?> getAllByCategory(@PathVariable("category-id") long categoryId,
                                              @RequestParam int page,
                                              @RequestParam int size) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity<?> getAllByMember(@PathVariable("member-id") long memberId,
                                            @RequestParam int page,
                                            @RequestParam int size) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}