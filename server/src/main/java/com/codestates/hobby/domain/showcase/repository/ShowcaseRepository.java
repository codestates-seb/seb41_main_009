package com.codestates.hobby.domain.showcase.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.showcase.entity.Showcase;

public interface ShowcaseRepository extends JpaRepository<Showcase, Long> {
	Optional<Showcase> findByIdAndMemberId(long showcaseId, long memberId);

	Page<Showcase> findAllByMemberIdOrderByIdDesc(long memberId, Pageable pageable);

	Page<Showcase> findAllByCategoryOrderByIdDesc(Category category, Pageable pageable);

	Page<Showcase> findAllByContentContainsOrderByIdDesc(String query, Pageable pageable);
}
