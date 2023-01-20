package com.codestates.hobby.domain.showcase.service;

import java.util.List;

import org.springframework.data.domain.Page;
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
import com.codestates.hobby.global.config.support.InfiniteScrollRequest;
import com.codestates.hobby.global.exception.BusinessLogicException;
import com.codestates.hobby.global.exception.ExceptionCode;

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

		Showcase showcase = getEditableShowcase(patch.getMemberId(), patch.getShowcaseId());
		showcase.update(category, patch.getContent(), fileInfos);

		return showcase;
	}

	@Transactional
	public void delete(long memberId, long showcaseId) {
		showcaseRepository.delete(getEditableShowcase(memberId, showcaseId));
	}

	@Transactional(readOnly = true)
	public Showcase findById(long showcaseId) {
		return showcaseRepository.findById(showcaseId)
			.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_SHOWCASE));
	}

	@Transactional(readOnly = true)
	public Page<Showcase> findAll(InfiniteScrollRequest isRequest) {
		return showcaseRepository.findAllByIdLessThan(getOffset(isRequest), isRequest.of());
	}

	@Transactional(readOnly = true)
	public Page<Showcase> findAllByMember(long memberId, InfiniteScrollRequest isRequest) {
		return showcaseRepository.findAllByMemberIdAndIdLessThan(memberId, getOffset(isRequest), isRequest.of());
	}

	@Transactional(readOnly = true)
	public Page<Showcase> findAllByCategory(String categoryName, InfiniteScrollRequest isRequest) {
		Category category = categoryService.findHobbyByName(categoryName);
		return showcaseRepository.findAllByCategoryAndIdLessThan(category, getOffset(isRequest), isRequest.of());
	}

	@Transactional(readOnly = true)
	public Page<Showcase> search(String query, InfiniteScrollRequest isRequest) {
		return showcaseRepository.findAllByContentContainsAndIdLessThan(query, getOffset(isRequest), isRequest.of());
	}

	private long getOffset(InfiniteScrollRequest isRequest) {
		return isRequest.getOffset() < 0 ? showcaseRepository.count() + 1 : isRequest.getOffset();
	}

	private Showcase getEditableShowcase(long memberId, long showcaseId) {
		Showcase showcase = findById(showcaseId);
		if (!showcase.isWrittenBy(memberId))
			throw new BusinessLogicException(ExceptionCode.NO_PERMISSION_TO_EDIT);
		return showcase;
	}
}
