package com.codestates.hobby.domain.series.service;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.category.service.CategoryService;
import com.codestates.hobby.global.exception.BusinessLogicException;
import com.codestates.hobby.global.exception.ExceptionCode;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.service.MemberService;
import com.codestates.hobby.domain.series.dto.SeriesDto;
import com.codestates.hobby.domain.series.entity.Series;
import com.codestates.hobby.domain.series.repository.SeriesRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeriesService {
    private final SeriesRepository seriesRepository;
    private final MemberService memberService;
    private final CategoryService categoryService;

    @Transactional
    public Series create(SeriesDto.Post post) {
        Member member = memberService.findMemberById(post.getMemberId());
        Category category = categoryService.findHobbyByName(post.getCategory());

        return seriesRepository.save(new Series(member, category, post.getTitle(), post.getContent(), post.getThumbnail()));
    }

    @Transactional
    public Series edit(SeriesDto.Patch patch, long loginId) {
        Category category = categoryService.findHobbyByName(patch.getCategory());
        Series series = findById(patch.getSeriesId());
        if(series.getMember().getId() != loginId) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);

        series.edit(category, patch.getTitle(), patch.getContent(), patch.getThumbnail());

        return seriesRepository.save(series);
    }

    @Transactional
    public void delete(long seriesId, long loginId) {
        Series series = findById(seriesId);
        if(series.getMember().getId() != loginId) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
        seriesRepository.deleteById(seriesId);
    }

    @Transactional(readOnly = true)
    public Series findById(long seriesId) {
        return seriesRepository.findById(seriesId).orElseThrow(()-> new BusinessLogicException(ExceptionCode.NOT_FOUND_SERIES));
    }

    @Transactional(readOnly = true)
    public Page<Series> findAll(PageRequest pageRequest) {
        return seriesRepository.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Series> findAllByCategory(String categoryName, PageRequest pageRequest) {
        Category category = categoryService.findHobbyByName(categoryName);
        return seriesRepository.findAllByCategoryOrderByIdDesc(category, pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Series> findAllByMember(long memberId, PageRequest pageRequest) {
        return seriesRepository.findAllByMemberIdOrderByIdDesc(memberId, pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Series> search() {
        return null;
    }


    @Transactional(readOnly = true)
    public Series findById(Long seriesId) {
        return seriesRepository.findById(seriesId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_SERIES));
    }
}
