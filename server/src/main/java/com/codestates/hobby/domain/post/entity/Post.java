package com.codestates.hobby.domain.post.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.*;
import com.codestates.hobby.domain.common.Writing;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import com.codestates.hobby.domain.category.entity.Category;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.series.entity.Series;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorValue("Post")
@NoArgsConstructor
public class Post extends Writing {
	@Column
	@ColumnDefault("0")
	private int commentCount;

	@Column
	private String  description;

	@Column
	private String thumbnailUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id")
	private Series series;

	@OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<FileInfo> images = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<PostComment> comments = new ArrayList<>();

	public Post(Member member, String title, Series series, Category category, String content, String description, List<String> imageURLs) {
		super(member, title, category, content);
		this.series = series;
		this.description = description;

		if (imageURLs == null) {
			this.thumbnailUrl = "https://st3.depositphotos.com/23594922/31822/v/600/depositphotos_318221368-stock-illustration-missing-picture-page-for-website.jpg";
		}
		else {
			this.thumbnailUrl = imageURLs.get(0);
			imageURLs.forEach(this::addImageFromUrl);
		}
	}

	public void updatePost (String title, String content, String description, Category category, Series series, List<String> imageURLs) {
		super.update(title, content, category);
		if (this.series != null && !this.series.equals(series)) this.series = series;
		if (this.series == null && series != null) this.series = series;
		this.description = description;

		if (imageURLs == null) {
			this.thumbnailUrl = "https://st3.depositphotos.com/23594922/31822/v/600/depositphotos_318221368-stock-illustration-missing-picture-page-for-website.jpg";
			images.clear();
		}
		else {
			this.thumbnailUrl = imageURLs.get(0);
			updateImage(imageURLs);
		}
	}

	public void addImageFromUrl(String url) {
		FileInfo fileInfo = new FileInfo(this, url, 0);
		images.add(fileInfo);
	}

	public void  updateImage(List<String> urls){
		List<String> olds = images.stream().map(FileInfo::getFileURL).collect(Collectors.toList());
		new ArrayList<>(images).stream().filter(image -> !urls.contains(image.getFileURL())).forEach(image -> images.remove(image));
		images.clear();
		urls.stream().filter(url -> !olds.contains(url)).forEach(this::addImageFromUrl);
	}

	public void deleteSeries() {
		this.series = null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		Post post = (Post) o;
		return getId() != null && Objects.equals(getId(), post.getImages());
	}

	public void updateCommentCount(int num){
		this.commentCount += num;
	}
}