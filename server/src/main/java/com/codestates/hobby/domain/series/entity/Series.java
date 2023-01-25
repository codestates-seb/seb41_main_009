package com.codestates.hobby.domain.series.entity;

import java.util.List;
import java.util.Optional;

import javax.persistence.*;

import com.codestates.hobby.domain.common.Writing;
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
@DiscriminatorValue("Series")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Series extends Writing {
	@OneToMany(mappedBy = "series")
	private List<Post> posts;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "file_info_id", updatable = false)
	private FileInfo image;

	public Series(Member member, Category category, String title, String content, String thumbnail) {
		super(member, title, category, content);
		this.changeImage(thumbnail);
	}

	public void edit(Category category, String title, String content, String thumbnail) {
		super.update(title, content, category);
		if(Optional.ofNullable(thumbnail).isPresent()) changeImage(thumbnail);
	}

	public void changeImage(String url) {
		this.image = new FileInfo(this, url, 0);
	}
}