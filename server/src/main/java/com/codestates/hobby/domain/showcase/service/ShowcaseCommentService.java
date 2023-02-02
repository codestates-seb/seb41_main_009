package com.codestates.hobby.domain.showcase.service;

import com.codestates.hobby.global.exception.BusinessLogicException;
import com.codestates.hobby.global.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.member.service.MemberService;
import com.codestates.hobby.domain.showcase.dto.ShowcaseCommentDto;
import com.codestates.hobby.domain.showcase.entity.Showcase;
import com.codestates.hobby.domain.showcase.entity.ShowcaseComment;
import com.codestates.hobby.domain.showcase.repository.ShowcaseCommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShowcaseCommentService {
	private final ShowcaseCommentRepository commentRepository;
	private final ShowcaseService showcaseService;
	private final MemberService memberService;

	@Transactional
	public ShowcaseComment comment(ShowcaseCommentDto.Post post) {
		Member member = memberService.findMemberById(post.getMemberId());
		Showcase showcase = showcaseService.findById(post.getShowcaseId());

		return commentRepository.save(new ShowcaseComment(post.getContent(), member, showcase));
	}

	@Transactional
	public void update(ShowcaseCommentDto.Patch patch) {
		findVerifiedComment(patch.getMemberId(), patch.getShowcaseId(), patch.getCommentId())
			.update(patch.getContent());
	}

	@Transactional
	public void delete(Long memberId, long showcaseId, long commentId) {
		commentRepository.delete(findVerifiedComment(memberId, showcaseId, commentId));
	}

	/************ Query ************/

	@Transactional(readOnly = true)
	public ShowcaseComment findById(Long commentId) {
		return commentRepository.findById(commentId)
			.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_COMMENT));
	}

	@Transactional(readOnly = true)
	public Page<ShowcaseComment> findAll(long showcaseId, PageRequest pageRequest) {
		return commentRepository.findAllByShowcaseIdOrderByIdDesc(showcaseId, pageRequest);
	}

	@Transactional(readOnly = true)
	public Page<ShowcaseComment> findAllByMember(long memberId, PageRequest pageRequest) {
		return commentRepository.findAllByMemberIdOrderByIdDesc(memberId, pageRequest);
	}

	private ShowcaseComment findVerifiedComment(long memberId, long showcaseId, long commentId) {
		return commentRepository
			.findByIdAndMemberIdAndShowcaseId(commentId, memberId, showcaseId)
			.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_COMMENT));
	}
}
