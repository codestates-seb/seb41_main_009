package com.codestates.hobby.domain.post.service;

import com.codestates.hobby.global.exception.BusinessLogicException;
import com.codestates.hobby.global.exception.ExceptionCode;
import com.codestates.hobby.domain.post.repository.PostRepository;
import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.category.service.CategoryService;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.service.MemberService;
import com.codestates.hobby.domain.post.dto.PostDto;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.series.entity.Series;
import com.codestates.hobby.domain.series.service.SeriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final SeriesService seriesService;
    private final PostValidationService postValidationService;
    private final Executor executor;

    @Transactional
    public Post post(PostDto.Post postDto){
        CompletableFuture<Member> member = CompletableFuture.supplyAsync(()->memberService.findMemberById(postDto.getMemberId()),executor);
        CompletableFuture<Category> category = CompletableFuture.supplyAsync(()->categoryService.findHobbyByName(postDto.getCategory()),executor);
        CompletableFuture<Series> series = CompletableFuture.supplyAsync(()->seriesService.findById(postDto.getSeriesId()),executor);

        Post post = new Post(member.join(), postDto.getTitle(),
                postDto.getSeriesId() != null ? series.join():null,
                category.join(),
                postDto.getContent(),
                postDto.getDescription(),
                postDto.getImgUrls() != null ? postDto.getImgUrls():null);

        if (postValidationService.isDuplicatedOrder()) {
            throw new BusinessLogicException(ExceptionCode.EXISTS_POST);
        }
        postValidationService.save(post.getMember().getId());

        return postRepository.save(post);
    }

    @Transactional
    public Post update(PostDto.Patch patchDto){
        Post findPost = findVerifiedPost(patchDto.getPostId());
        isMatchMember(findPost, patchDto.getMemberId());
        CompletableFuture<Category> category = CompletableFuture.supplyAsync(()->categoryService.findHobbyByName(patchDto.getCategory()),executor);
        CompletableFuture<Series> series = CompletableFuture.supplyAsync(()->seriesService.findById(patchDto.getSeriesId()),executor);

        findPost.updatePost(patchDto.getTitle(),
                patchDto.getContent(),
                patchDto.getDescription(),
                category.join(),
                patchDto.getSeriesId() != null ? series.join():null,
                patchDto.getImgUrls() != null ? patchDto.getImgUrls():null);

        return findPost;
    }
    @Transactional
    public void delete(long postId, long memberId){
        Post findPost = findVerifiedPost(postId);
        isMatchMember(findPost,memberId);
        postRepository.delete(findPost);
    }

    @Transactional
    public Post findById(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new BusinessLogicException(ExceptionCode.NOT_FOUND_POST));
        post.addViews();
        return post;
    }

    @Transactional(readOnly = true)
    public Page<Post> findByContent(String content, PageRequest pageRequest) {
        return postRepository.findAllByContentContainsOrderByIdDesc(content,pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAll(PageRequest pageRequest) {
        return postRepository.findAllByOrderByIdDesc(pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllByMember(long memberId, PageRequest pageRequest) {
        return postRepository.findAllByMemberIdOrderByIdDesc(memberId,pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllByCategory(String categoryName, PageRequest pageRequest) {
        return postRepository.findAllByCategoryOrderByIdDesc(categoryService.findHobbyByName(categoryName),pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAllBySeries(long seriesId, PageRequest pageRequest) {
        return postRepository.findAllBySeriesIdOrderByIdDesc(seriesId, pageRequest);
    }

    public Post findVerifiedPost(long postId){
        Optional<Post> optionalPost = postRepository.findById(postId);
        Post findPost = optionalPost.orElseThrow(() ->new BusinessLogicException(ExceptionCode.NOT_FOUND_POST));
        return findPost;
    }

    public boolean isMatchMember(Post post, long memberId){
        boolean isMatch = post.getMember().getId().equals(memberId);
        if (!isMatch) throw new BusinessLogicException(ExceptionCode.NOT_MATCH_MEMBER);
        return post.getMember().getId().equals(memberId);
    }
}