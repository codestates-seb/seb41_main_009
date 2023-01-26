package com.codestates.hobby.domain.category.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.codestates.hobby.global.config.CachingConfig;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = CachingConfig.CATEGORY_CACHE)
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String korName;

	@Column(nullable = false, unique = true)
	private String engName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = CachingConfig.CATEGORY_CACHE)
	private Category group;

	@OneToMany(mappedBy = "group")
	private List<Category> categories = new ArrayList<>();

	private Category(String korName, String engName, Category group) {
		this.korName = korName;
		this.engName = engName;
		this.group = group;
	}

	public static Category createParent(String korName, String engName) {
		return new Category(korName, engName, null);
	}

	public static Category createChild(String korName, String engName, Category parent) {
		return new Category(korName, engName, parent);
	}

	public boolean isGroup() {
		return group == null;
	}
}