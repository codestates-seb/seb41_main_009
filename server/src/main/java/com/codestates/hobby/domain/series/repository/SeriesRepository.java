package com.codestates.hobby.domain.series.repository;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.series.entity.Series;
import com.codestates.hobby.domain.showcase.entity.Showcase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    Optional<Series> findByIdAndMemberId(long seriesId, long memberId);

    Page<Series> findAllByMemberIdOrderByIdDesc(long memberId, Pageable pageable);

    Page<Series> findAllByCategoryOrderByIdDesc(Category category, Pageable pageable);
}
