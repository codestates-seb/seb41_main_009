package com.codestates.hobby.domain.post.controller;

import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.post.dto.PostDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostCommendController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody PostDto.Post postDto,
                                  @SessionAttribute Member loginMember) {
        postDto.setMemberId(loginMember.getId());
        Post post = postService.post(postDto);
        return new ResponseEntity<>(post.getId(), HttpStatus.CREATED);
    }

    @PatchMapping("/{post-id}")
    public ResponseEntity<?> patch(@Valid @RequestBody PostDto.Patch patchDto,
                                   @PathVariable("post-id") long postId,
                                   @SessionAttribute Member loginMember) {
        patchDto.setProperties(loginMember.getId(), postId);
        Post post = postService.update(patchDto);
        return new ResponseEntity<>(post.getId(),HttpStatus.OK);
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity<?> delete(@PathVariable("post-id") long postId,
                                    @SessionAttribute Member loginMember) {
        postService.delete(postId, loginMember.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
