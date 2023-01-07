package com.codestates.hobby.domain.category.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.series.entity.Series;
import com.codestates.hobby.domain.showcase.entity.Showcase;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private List<Category> children;

	@OneToMany(mappedBy = "category")
	private List<Series> series;

	@OneToMany(mappedBy = "category")
	private List<Post> posts;

	@OneToMany(mappedBy = "category")
	private List<Showcase> showcases;

	private Category(String name, Category parent) {
		this.name = name;
		this.parent = parent;
	}

	public static Category createParent(String name) {
		return new Category(name, null);
	}

	public static Category createChild(String name, Category parent) {
		return new Category(name, parent);
	}

	public boolean isParent() {
		return parent != null;
	}
}