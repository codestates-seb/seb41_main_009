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
	public Showcase post(ShowcaseDto.Post postDto) {
		// 0. 검증
		// 1. 이미지를 저장한다.
		// 2. 성공하면 Showcase를 저장한다.
		//    - 실패시 저장된 이미지를 지워야됨
		// * (Optional) 구독자들에게 알림
		return null;
	}

	@Transactional
	public Showcase update(ShowcaseDto.Patch patch) {
		// 0. 검증
		// 1. 이미지를 저장한다. (변경됐다면)
		// 2. 성공하면 Showcase를 수정한다.
		//    - 실패시 저장된 이미지를 지워야됨 (변경된것만)
		return null;
	}

	@Transactional
	public void delete(long memberId, long showcaseId) {

	}

	@Transactional(readOnly = true)
	public Showcase findById(long showcaseId) {
		return null;
	}

	@Transactional(readOnly = true)
	public Page<Showcase> findAll(PageRequest pageRequest) {
		return null;
	}

	@Transactional(readOnly = true)
	public Page<Showcase> findAllByMember(long memberId, PageRequest pageRequest) {
		return null;
	}

	@Transactional(readOnly = true)
	public Page<Showcase> findAllByCategory(String categoryName, PageRequest pageRequest) {
		return null;
	}

	@Transactional(readOnly = true)
	public Page<Showcase> search(String query, PageRequest pageRequest) {
		return null;
	}
}
