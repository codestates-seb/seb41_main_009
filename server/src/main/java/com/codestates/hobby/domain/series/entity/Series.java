package com.codestates.hobby.domain.series.entity;

import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import org.hibernate.annotations.ColumnDefault;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.common.BaseEntity;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.post.entity.Post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Series extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@Column
	@ColumnDefault("0")
	private int views;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false, updatable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@OneToMany(mappedBy = "series")
	private List<Post> posts;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "file_info_id", updatable = false)
	private FileInfo image;

	public Series(Member member, Category category, String title, String content, String thumbnail) {
		this.member = member;
		this.category = category;
		this.title = title;
		this.content = content;
		this.changeImage(thumbnail);
	}

	public void edit(Category category, String title, String content, String thumbnail) {
		if(!this.category.getId().equals(category.getId()))  this.category = category;
		if(Optional.ofNullable(title).isPresent()) this.title = title;
		if(Optional.ofNullable(content).isPresent()) this.content = content;
		if(Optional.ofNullable(thumbnail).isPresent()) changeImage(thumbnail);
	}

	public void changeImage(String url) {
		this.image = new FileInfo(this, url, 0);
	}
}