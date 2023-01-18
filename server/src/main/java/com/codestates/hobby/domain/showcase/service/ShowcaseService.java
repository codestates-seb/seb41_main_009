package com.codestates.hobby.domain.showcase.service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.category.service.CategoryService;
import com.codestates.hobby.domain.fileInfo.dto.BasePath;
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
	public Showcase post(ShowcaseDto.Post post) {
		List<FileInfo> fileInfos = fileInfoService.generateSignedURLs(post.getFileInfos(), BasePath.SHOWCASES);
		Category category = categoryService.findHobbyByName(post.getCategory());
		Member member = memberService.findMemberById(post.getMemberId());

		return showcaseRepository.save(new Showcase(post.getContent(), member, category, fileInfos));
	}

	@Transactional
	public Showcase update(ShowcaseDto.Patch patch) {
		List<FileInfo> fileInfos = fileInfoService.generateSignedURLs(patch.getFileInfos(), BasePath.SHOWCASES);
		Category category = categoryService.findHobbyByName(patch.getCategory());
		Showcase showcase = showcaseRepository.findByIdAndMemberId(patch.getShowcaseId(), patch.getMemberId())
			.orElseThrow(() -> new IllegalArgumentException("Not Found showcase for " + patch.getShowcaseId()));

		showcase.update(category, patch.getContent(), fileInfos);
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

	@Transactional
	protected void arrangeFileIndexes(List<FileInfo> fileInfos) {
		Set<Integer> set = fileInfos.stream().map(FileInfo::getIndex).collect(Collectors.toSet());
		fileInfos.sort(Comparator.comparingInt(FileInfo::getIndex));

		if (fileInfos.size() != set.size()) {
			for (int i = 0; i < fileInfos.size(); i++)
				fileInfos.get(i).updateIndex(i);
		}
	}
}
