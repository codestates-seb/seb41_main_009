package com.codestates.hobby.domain.series.repository;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.series.entity.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    Page<Series> findAllByOrderByIdDesc(Pageable pageable);
    Page<Series> findAllByMemberIdOrderByIdDesc(long memberId, Pageable pageable);

    Page<Series> findAllByCategoryOrderByIdDesc(Category category, Pageable pageable);

    Page<Series> findByContentContainsOrderByIdDesc(String query, Pageable pageable);
}
