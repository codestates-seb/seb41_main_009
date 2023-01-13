package com.codestates.hobby.domain.showcase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codestates.hobby.domain.showcase.entity.ShowcaseImage;

public interface ShowcaseImageRepository extends JpaRepository<ShowcaseImage, Long> {
	List<ShowcaseImage> findAllByShowcaseId(long id);
}
