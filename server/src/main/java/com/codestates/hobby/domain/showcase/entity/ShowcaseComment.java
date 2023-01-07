package com.codestates.hobby.domain.showcase.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.codestates.hobby.domain.common.Comment;
import com.codestates.hobby.domain.member.entity.Member;

import lombok.Getter;

@Getter
@Entity
public class ShowcaseComment extends Comment {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "showcase_id", nullable = false, updatable = false)
	private Showcase showcase;

	protected ShowcaseComment() {
		super();
	}

	public ShowcaseComment(String content, Member member, Showcase showcase) {
		super(content, member);
		this.showcase = showcase;
	}
}
