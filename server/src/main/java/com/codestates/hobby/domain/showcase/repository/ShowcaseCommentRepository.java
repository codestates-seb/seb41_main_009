package com.codestates.hobby.domain.showcase.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codestates.hobby.domain.showcase.entity.ShowcaseComment;

public interface ShowcaseCommentRepository extends JpaRepository<ShowcaseComment, Long> {
	long countAllByShowcaseId(long showcaseId);

	Optional<ShowcaseComment> findByIdAndMemberIdAndShowcaseId(long commentId, long memberId, long showcaseId);

	Page<ShowcaseComment> findAllByMemberIdOrderByIdDesc(long memberId, Pageable pageable);

	Page<ShowcaseComment> findAllByShowcaseIdOrderByIdDesc(long showcaseId, Pageable pageable);
}
