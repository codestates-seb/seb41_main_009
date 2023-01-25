package com.codestates.hobby.domain.showcase.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codestates.hobby.domain.showcase.dto.CommentProjection;
import com.codestates.hobby.domain.showcase.entity.ShowcaseComment;

public interface ShowcaseCommentRepository extends JpaRepository<ShowcaseComment, Long> {
	long countAllByShowcaseId(long showcaseId);

	Optional<ShowcaseComment> findByIdAndMemberIdAndShowcaseId(long commentId, long memberId, long showcaseId);

	@Query(value = "select c from ShowcaseComment c join fetch c.member m join fetch m.image where c.member.id = :memberId",
		countQuery = "select c from ShowcaseComment c where c.member.id = :memberId")
	Page<ShowcaseComment> findAllByMemberIdOrderByIdDesc(long memberId, Pageable pageable);

	@Query(value = "select c from ShowcaseComment c join fetch c.member m join fetch m.image where c.showcase.id = :showcaseId",
		countQuery = "select c from ShowcaseComment c where c.showcase.id = :showcaseId")
	Page<ShowcaseComment> findAllByShowcaseIdOrderByIdDesc(long showcaseId, Pageable pageable);

	@Query("select c from ShowcaseComment c join fetch c.member m join fetch m.image where c.id in :ids")
	List<ShowcaseComment> findAllByIdUsingFetch(Set<Long> ids);

	@Query("select max(cc.id) as id, count(cc.id) as count from ShowcaseComment cc where cc.showcase.id in :showcaseId group by cc.showcase.id")
	List<CommentProjection> findAllLastIdByShowcaseId(Set<Long> showcaseId);
}
