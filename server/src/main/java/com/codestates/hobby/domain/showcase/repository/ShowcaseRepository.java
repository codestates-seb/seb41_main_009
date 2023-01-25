package com.codestates.hobby.domain.showcase.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.showcase.entity.Showcase;

public interface ShowcaseRepository extends JpaRepository<Showcase, Long> {
	@Query("select s from Showcase s join fetch s.member m where s.id = ?1")
	Optional<Showcase> findByIdUsingFetch(long showcaseId);

	@Query("select s from Showcase s join fetch s.member where s.member.id = :memberId and s.id < :showcaseId")
	Slice<Showcase> findAllByMemberIdAndIdLessThan(long memberId, long showcaseId, Pageable pageable);

	@Query("select s from Showcase s join fetch s.member where s.id < :showcaseId")
	Slice<Showcase> findAllByIdLessThan(long showcaseId, Pageable pageable);

	@Query("select s from Showcase s join fetch s.member where s.category = :category and s.id < :showcaseId")
	Slice<Showcase> findAllByCategoryAndIdLessThan(Category category, long showcaseId, Pageable pageable);

	@Query("select s from Showcase s join fetch s.member where s.content like concat('%', :query, '%') and s.id < :showcaseId")
	Slice<Showcase> findAllByContentContainsAndIdLessThan(String query, long showcaseId, Pageable pageable);
}
