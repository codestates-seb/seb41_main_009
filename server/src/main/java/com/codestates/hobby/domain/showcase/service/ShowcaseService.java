package com.codestates.hobby.domain.showcase.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.category.service.CategoryService;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.fileInfo.service.FileInfoService;
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
	private final FileInfoService fileInfoService;
	private final MemberService memberService;

	@Transactional
	public Showcase post(ShowcaseDto.Post postDto) {
		Member member = memberService.findVerifiedById(postDto.getMemberId());
		Category category = categoryService.findHobbyByName(postDto.getCategory());
		List<FileInfo> fileInfos = fileInfoService.saveAllByUrls(postDto.getImageUrls());

		return new Showcase(postDto.getContent(), member, category, fileInfos);
	}

	@Transactional
	public Showcase update(ShowcaseDto.Patch patch) {
		Category category = categoryService.findHobbyByName(patch.getCategory());
		Showcase showcase = showcaseRepository.findByIdAndMemberId(patch.getShowcaseId(), patch.getMemberId())
			.orElseThrow(() -> new IllegalArgumentException("Not Found showcase for " + patch.getShowcaseId()));

		fileInfoService.delete(showcase.getFileInfos());
		showcase.update(fileInfoService.saveAllByUrls(patch.getImageUrls()), category, patch.getContent());
		return showcase;
	}

	@Transactional
	public void delete(long memberId, long showcaseId) {
		showcaseRepository.findByIdAndMemberId(showcaseId, memberId);
	}

	@Transactional(readOnly = true)
	public Showcase findById(long showcaseId) {
		return showcaseRepository.findById(showcaseId)
			.orElseThrow(() -> new IllegalArgumentException("Not found for " + showcaseId));
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
