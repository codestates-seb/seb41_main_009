package com.codestates.hobby.domain.showcase.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codestates.hobby.domain.showcase.entity.ShowcaseComment;

@Service
public class ShowcaseCommentService {
	@Transactional
	public void post(long memberId, long showcaseId, String content) {

	}

	@Transactional
	public void update(long memberId, long showcaseId, long commentId, String content) {

	}

	@Transactional
	public void delete(long memberId, long showcaseId, long commentId) {

	}

	@Transactional(readOnly = true)
	public int getCount(long showcaseId) {
		return 0;
	}

	@Transactional(readOnly = true)
	public Page<ShowcaseComment> findAll(long showcaseId, int page, int size) {
		return null;
	}
}
