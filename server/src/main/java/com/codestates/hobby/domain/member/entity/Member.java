package com.codestates.hobby.domain.member.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;

import com.codestates.hobby.domain.common.BaseEntity;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.notifcation.entity.Notification;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.post.entity.PostComment;
import com.codestates.hobby.domain.series.entity.Series;
import com.codestates.hobby.domain.showcase.entity.Showcase;
import com.codestates.hobby.domain.showcase.entity.ShowcaseComment;
import com.codestates.hobby.domain.subscribe.entity.Subscribe;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, updatable = false)
	private String email;

	@Column(nullable = false, unique = true, updatable = false)
	private String nickname;

	@Column(nullable = false)
	private String password;

	private String introduction;

	private boolean isOauth2;

	@OneToMany(mappedBy = "member")
	private List<Series> series;

	@OneToMany(mappedBy = "member")
	private List<Post> posts;

	@OneToMany(mappedBy = "member")
	private List<Showcase> showcases;

	@OneToMany(mappedBy = "member")
	private List<PostComment> postComments;

	@OneToMany(mappedBy = "member")
	private List<ShowcaseComment> showcaseComments;

	@OneToMany(mappedBy = "target")
	private List<Notification> notifications;

	@OneToMany(mappedBy = "target")
	private List<Subscribe> subscribes;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "MEMBER_IMAGE",
		joinColumns = @JoinColumn(name = "member_id"),
		inverseJoinColumns = @JoinColumn(name = "file_info_id"),
		uniqueConstraints = @UniqueConstraint(name = "unq_member_profile", columnNames = "member_id"))
	private FileInfo profileImage;

	public Member(String email, String nickname, String password, boolean isOauth2) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.isOauth2 = isOauth2;
	}
}
