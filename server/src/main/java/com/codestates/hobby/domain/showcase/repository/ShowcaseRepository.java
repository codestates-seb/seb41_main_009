package com.codestates.hobby.domain.showcase.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.showcase.entity.Showcase;

public interface ShowcaseRepository extends JpaRepository<Showcase, Long> {
	@Query("select s from Showcase s "
		+ "join fetch s.member m "
		+ "join fetch s.category c "
		+ "where s.id = ?1")
	Optional<Showcase> findById(long showcaseId, long memberId);

	Page<Showcase> findAllByMemberIdAndIdLessThan(long memberId, long showcaseId, Pageable pageable);

	Page<Showcase> findAllByIdLessThan(long showcaseId, Pageable pageable);

	Page<Showcase> findAllByCategoryAndIdLessThan(Category category, long showcaseId, Pageable pageable);

	Page<Showcase> findAllByContentContainsAndIdLessThan(String query, long showcaseId, Pageable pageable);
}
