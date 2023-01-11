package com.codestates.hobby.domain.post.entity;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.common.BaseEntity;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.series.entity.Series;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false, columnDefinition = "MEDIUMTEXT")
	private String content;

	@Column
	@ColumnDefault("0")
	private int views;

	@Column
	@ColumnDefault("false")
	private boolean isTemp;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "POST_IMAGE",
			joinColumns = @JoinColumn(name = "POST_ID"),
			inverseJoinColumns = @JoinColumn(name = "FILE_INFO_ID"))
	private FileInfo thumbnail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id", nullable = false)
	private Series series;

	@OneToMany(mappedBy = "post")
	private List<PostComment> comments;

	public Post(Member member, String title, Category category, String content) {
		this(member, title, null, content, category);
	}

	public Post(Member member, String title, Series series, String content, Category category) {
		this.title = title;
		this.content = content;
		this.member = member;
		this.category = category;
		this.series = series;
	}
}
