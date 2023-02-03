package com.codestates.hobby.domain.post.repository;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    @EntityGraph(attributePaths = {"member"})
    Optional<Post> findById(@Param("postId") long postId);

    @EntityGraph(attributePaths = {"member"})
    Page<Post> findAllByOrderByIdDesc(Pageable pageable);

    @EntityGraph(attributePaths = {"member"})
    Page<Post> findAllByMemberIdOrderByIdDesc(@Param("memberId")long memberId, Pageable pageable);

    @EntityGraph(attributePaths = {"member"})
    Page<Post> findAllByCategoryOrderByIdDesc(@Param("category")Category category, Pageable pageable);

    @EntityGraph(attributePaths = {"member"})
    Page<Post> findAllBySeriesIdOrderByIdDesc(@Param("seriesId")long seriesId, Pageable pageable);

    @EntityGraph(attributePaths = {"member"})
    Page<Post> findAllByContentContainsOrderByIdDesc(@Param("query")String query, Pageable pageable);
}
