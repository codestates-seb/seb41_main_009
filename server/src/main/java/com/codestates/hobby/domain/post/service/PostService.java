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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final SeriesService seriesService;

    @Transactional
    public Post post(PostDto.Post postDto){
        Member member = memberService.findMemberById(postDto.getMemberId());
        Category category = categoryService.findHobbyByName(postDto.getCategory());
        if (postDto.getSeriesId() != null){
            Series series = seriesService.findById(postDto.getSeriesId());
            if (postDto.getImgUrls() != null) return postRepository.save(new Post(member,postDto.getTitle(),series,category,postDto.getContent(),postDto.getImgUrls()));
            else return postRepository.save(new Post(member,postDto.getTitle(),series,category,postDto.getContent()));
        }
        else {
            if (postDto.getImgUrls() != null) return postRepository.save(new Post(member,postDto.getTitle(),category,postDto.getContent(),postDto.getImgUrls()));
            else return postRepository.save(new Post(member,postDto.getTitle(),category,postDto.getContent()));
        }
    }

    @Transactional
    public Post update(PostDto.Patch patchDto){
        Post findPost = findVerifiedPost(patchDto.getPostId());
        isMatchMember(findPost, patchDto.getMemberId());
        if (patchDto.getSeriesId() != null) {
            if (patchDto.getImgUrls() != null) findPost.updatePost(patchDto.getTitle(), patchDto.getContent(),
                    categoryService.findHobbyByName(patchDto.getCategory()), seriesService.findById(patchDto.getSeriesId()), patchDto.getImgUrls());
            else findPost.updatePost(patchDto.getTitle(), patchDto.getContent(), categoryService.findHobbyByName(patchDto.getCategory()),
                    seriesService.findById(patchDto.getSeriesId()));
        } else {
            if (patchDto.getImgUrls() != null) findPost.updatePost(patchDto.getTitle(), patchDto.getContent()
                    , categoryService.findHobbyByName(patchDto.getCategory()), patchDto.getImgUrls());
            else findPost.updatePost(patchDto.getTitle(), patchDto.getContent()
                    , categoryService.findHobbyByName(patchDto.getCategory()));
        }
        return postRepository.save(findPost);
    }
    @Transactional
    public void delete(long postId, long memberId){
        Post findPost = findVerifiedPost(postId);
        isMatchMember(findPost,memberId);
        postRepository.delete(findPost);
    }

    @Transactional
    public Post findById(long postId) {
        return findVerifiedPost(postId);
    }

    @Transactional(readOnly = true)
    public Page<Post> findByContent(String content, PageRequest pageRequest) {
        return postRepository.findAllByContentContainsOrderByIdDesc(content,pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Post> findAll(PageRequest pageRequest) {
        return postRepository.findAll(pageRequest.withSort(Sort.by("id").descending()));
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
