package com.codestates.hobby.domain.post.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.codestates.hobby.domain.common.Comment;
import com.codestates.hobby.domain.member.entity.Member;

import lombok.Getter;

@Getter
@Entity
public class PostComment extends Comment {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false, updatable = false)
	private Post post;

	protected PostComment() {
		super();
	}

	public PostComment(String content, Member member, Post post) {
		super(content, member);
		this.post = post;
	}
}
