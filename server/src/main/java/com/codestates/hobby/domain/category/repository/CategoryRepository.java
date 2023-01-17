package com.codestates.hobby.domain.category.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.codestates.hobby.domain.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	@Query("select c from Category c where c.korName = ?1 or c.engName = ?1")
	Optional<Category> findByKorNameOrEngName(String name);

	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	@Query("select c from Category c where c.group is null and (c.korName = ?1 or c.engName = ?1)")
	Optional<Category> findByGroupIsNullAndName(String name);

	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	@Query("select c from Category c where c.group is not null and (c.korName = ?1 or c.engName = ?1)")
	Optional<Category> findByGroupIsNotNullAndName(String name);

	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	List<Category> findAllByGroupIsNull();

	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	List<Category> findAllByGroup(Category group);

	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	@Query("select distinct c from Category c join fetch c.categories where c.group is null")
	List<Category> findAllGroupsWithHobbies();
}
