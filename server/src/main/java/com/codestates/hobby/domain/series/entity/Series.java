package com.codestates.hobby.domain.series.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.common.BaseEntity;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
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
	private String thumbnail;

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

/*	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "SERIES_IMAGE",
		joinColumns = @JoinColumn(name = "SERIES_ID"),
		inverseJoinColumns = @JoinColumn(name = "FILE_INFO_ID"))
	private FileInfo thumbnail;*/

	public Series(Member member, Category category, String title, String content, String thumbnail) {
		this.member = member;
		this.category = category;
		this.title = title;
		this.content = content;
		this.thumbnail = thumbnail;
	}

	public void edit(Category category, String title, String content, String thumbnail) {
		if(!Objects.equals(this.category.getId(), category.getId()))  this.category = category;
		if (!this.title.equals(title)) this.title = title;
		if (!this.title.equals(content)) this.title = content;
		if (!this.title.equals(thumbnail)) this.title = thumbnail;
	}

	public void addImageFromUrl(String url) {
	}
}