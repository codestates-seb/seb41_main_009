package com.codestates.hobby.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostQueryController {
    @GetMapping("/posts/{post-id}")
    public ResponseEntity<?> get(@PathVariable("post-id") long postId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAll(@RequestParam int page,
                                    @RequestParam int size) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/categories/{category-name}/posts")
    public ResponseEntity<?> getAllByCategory(@PathVariable("category-name") String categoryName,
                                              @RequestParam int page,
                                              @RequestParam int size) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/members/{member-id}/posts")
    public ResponseEntity<?> getAllByMember(@PathVariable("member-id") long memberId,
                                            @RequestParam int page,
                                            @RequestParam int size) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/series/{series-id}/posts")
    public ResponseEntity<?> getAllBySeries(@PathVariable("series-id") long seriesId,
                                            @RequestParam int page,
                                            @RequestParam int size) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}