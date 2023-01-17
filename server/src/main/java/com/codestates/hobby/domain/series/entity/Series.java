package com.codestates.hobby.domain.series.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

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
		FileInfo.createSeriesImage(this, thumbnail);
	}

	public void edit(Category category, String title, String content, String thumbnail) {
		if (!this.category.getId().equals(category.getId()))  this.category = category;
		if (!this.title.equals(title)) this.title = title;
		if (!this.content.equals(content)) this.content = content;
		if (!this.image.getFileURL().equals(thumbnail)) FileInfo.createSeriesImage(this, thumbnail);
	}
}