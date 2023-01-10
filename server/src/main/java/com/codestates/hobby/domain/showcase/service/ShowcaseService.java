package com.codestates.hobby.domain.showcase.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codestates.hobby.domain.showcase.dto.ShowcaseDto;
import com.codestates.hobby.domain.showcase.entity.Showcase;

@Service
public class ShowcaseService {
	@Transactional
	public void post(ShowcaseDto.Post postDto) {
		// 0. 검증
		// 1. 이미지를 저장한다.
		// 2. 성공하면 Showcase를 저장한다.
		//    - 실패시 저장된 이미지를 지워야됨
		// * (Optional) 구독자들에게 알림
	}

	@Transactional
	public void update(ShowcaseDto.Patch patch) {
		// 0. 검증
		// 1. 이미지를 저장한다. (변경됐다면)
		// 2. 성공하면 Showcase를 수정한다.
		//    - 실패시 저장된 이미지를 지워야됨 (변경된것만)
	}

	@Transactional
	public void delete(long memberId, long showcaseId) {

	}

	@Transactional
	public Showcase findById(long showcaseId) {
		// 조회수를 증가시킨다.
		return null;
	}

	@Transactional(readOnly = true)
	public Showcase findWithoutIncreasingViewsById(long showcaseId) {
		return null;
	}

	@Transactional(readOnly = true)
	public Page<Showcase> findAll(PageRequest pageRequest, boolean sortByNewest) {
		return null;
	}

	@Transactional(readOnly = true)
	public Page<Showcase> findAllByMember(long memberId, PageRequest pageRequest, boolean sortByNewest) {
		return null;
	}

	@Transactional(readOnly = true)
	public Page<Showcase> findAllByCategory(String categoryName, PageRequest pageRequest, boolean sortByNewest) {
		return null;
	}

	@Transactional(readOnly = true)
	public Page<Showcase> search(String query, PageRequest pageRequest) {
		return null;
	}
}
