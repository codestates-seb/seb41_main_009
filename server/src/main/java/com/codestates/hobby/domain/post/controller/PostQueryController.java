package com.codestates.hobby.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostQueryController {
    @GetMapping("/post/{post-id}")
    public ResponseEntity<?> get(@AuthenticationPrincipal Long memberId, @PathVariable("post-id") long postId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<?> getAll(@AuthenticationPrincipal Long memberId,
                                    @RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "1") int size,
                                    @RequestParam(defaultValue = "NEWEST") String sort) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/categories/{category-name}/posts")
    public ResponseEntity<?> getAllByCategory(@AuthenticationPrincipal Long memberId,
                                              @RequestParam(defaultValue = "1") int page,
                                              @RequestParam(defaultValue = "1") int size,
                                              @RequestParam(defaultValue = "NEWEST") String sort,
                                              @PathVariable("category-name") String category) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/members/{member-id}/posts")
    public ResponseEntity<?> getAllByMember(@AuthenticationPrincipal Long authMemberId,
                                            @PathVariable("member-id") long memberId,
                                            @RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "1") int size,
                                            @RequestParam(defaultValue = "NEWEST") String sort) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/series/{series-id}/posts")
    public ResponseEntity<?> getAllBySeries(@AuthenticationPrincipal Long authMemberId,
                                            @PathVariable("series-id") long seriesId,
                                            @RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "1") int size,
                                            @RequestParam(defaultValue = "NEWEST") String sort) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}