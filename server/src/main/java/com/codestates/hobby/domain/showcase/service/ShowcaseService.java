package com.codestates.hobby.domain.showcase.service;

import com.codestates.hobby.global.exception.BusinessLogicException;
import com.codestates.hobby.global.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.category.service.CategoryService;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.service.MemberService;
import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.codestates.hobby.domain.showcase.entity.Showcase;
import com.codestates.hobby.domain.showcase.repository.ShowcaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowcaseService {
	private final ShowcaseRepository showcaseRepository;
	private final CategoryService categoryService;
	private final MemberService memberService;

	@Transactional
	public Showcase post(ShowcaseDto.Post postDto) {
		Member member = memberService.findMemberById(postDto.getMemberId());
		Category category = categoryService.findHobbyByName(postDto.getCategory());

		return showcaseRepository.save(new Showcase(postDto.getContent(), member, category, postDto.getImageUrls()));
	}

	@Transactional
	public Showcase update(ShowcaseDto.Patch patch) {
		Category category = categoryService.findHobbyByName(patch.getCategory());
		Showcase showcase = showcaseRepository.findByIdAndMemberId(patch.getShowcaseId(), patch.getMemberId())
			.orElseThrow(() -> new BusinessLogicException(ExceptionCode.SHOWCASE_NOT_FOUND));

		showcase.update(category, patch.getContent(), patch.getImageUrls());

		return showcase;
	}

	@Transactional
	public void delete(long memberId, long showcaseId) {
		showcaseRepository.findByIdAndMemberId(showcaseId, memberId);
	}

	@Transactional(readOnly = true)
	public Showcase findById(long showcaseId) {
		return showcaseRepository.findById(showcaseId)
			.orElseThrow(() -> new BusinessLogicException(ExceptionCode.SHOWCASE_NOT_FOUND));
	}

	@Transactional(readOnly = true)
	public Page<Showcase> findAll(PageRequest pageRequest) {
		return showcaseRepository.findAll(pageRequest.withSort(Sort.by("id").descending()));
	}

	@Transactional(readOnly = true)
	public Page<Showcase> findAllByMember(long memberId, PageRequest pageRequest) {
		return showcaseRepository.findAllByMemberIdOrderByIdDesc(memberId, pageRequest);
	}

	@Transactional(readOnly = true)
	public Page<Showcase> findAllByCategory(String categoryName, PageRequest pageRequest) {
		Category category = categoryService.findHobbyByName(categoryName);
		return showcaseRepository.findAllByCategoryOrderByIdDesc(category, pageRequest);
	}

	@Transactional(readOnly = true)
	public Page<Showcase> search(String query, PageRequest pageRequest) {
		return showcaseRepository.findAllByContentContainsOrderByIdDesc(query, pageRequest);
	}
}
