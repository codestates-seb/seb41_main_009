package com.codestates.hobby.domain.post.controller;

import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostQueryController {
    private final PostService postService;

    @GetMapping("/posts/{post-id}")
    public ResponseEntity<?> get(@PathVariable("post-id") long postId, @AuthenticationPrincipal Long memberId) {
        Post post = postService.findById(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAll(@AuthenticationPrincipal Long memberId,
                                    @RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "1") int size,
                                    @RequestParam(defaultValue = "NEWEST") String sort) {
        Page<Post> posts = postService.findAll(page, size, sort.equalsIgnoreCase("NEWEST"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/categories/{category-name}/posts")
    public ResponseEntity<?> getAllByCategory(@AuthenticationPrincipal Long memberId,
                                              @RequestParam(defaultValue = "1") int page,
                                              @RequestParam(defaultValue = "1") int size,
                                              @RequestParam(defaultValue = "NEWEST") String sort,
                                              @PathVariable("category-name") String category) {
        Page<Post> posts = postService.findAllByCategory(category, page, size, sort.equalsIgnoreCase("NEWEST"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/members/{member-id}/posts")
    public ResponseEntity<?> getAllByMember(@AuthenticationPrincipal Long authMemberId,
                                            @PathVariable("member-id") long memberId,
                                            @RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "1") int size,
                                            @RequestParam(defaultValue = "NEWEST") String sort) {
        Page<Post> posts = postService.findAllByMember(memberId, page, size, sort.equalsIgnoreCase("NEWEST"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/series/{series-id}/posts")
    public ResponseEntity<?> getAllBySeries(@AuthenticationPrincipal Long authMemberId,
                                            @PathVariable("series-id") long seriesId,
                                            @RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "1") int size,
                                            @RequestParam(defaultValue = "NEWEST") String sort) {
        Page<Post> posts = postService.findAllBySeries(seriesId, page, size, sort.equalsIgnoreCase("NEWEST"));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}