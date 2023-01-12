package com.codestates.hobby.domain.category.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codestates.hobby.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query("select c from Category c where c.korName = ?1 or c.engName = ?1")
	Optional<Category> findByKorNameOrEngName(String name);

	@Query("select c from Category c where c.group is null and (c.korName = ?1 or c.engName = ?1)")
	Optional<Category> findByGroupIsNullAndName(String name);

	@Query("select c from Category c where c.group is not null and (c.korName = ?1 or c.engName = ?1)")
	Optional<Category> findByGroupIsNotNullAndName(String name);

	List<Category> findAllByGroupIsNull();

	List<Category> findAllByGroup(Category group);
}
