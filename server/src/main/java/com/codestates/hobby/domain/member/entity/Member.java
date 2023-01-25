package com.codestates.hobby.domain.member.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.*;

import com.codestates.hobby.domain.common.BaseEntity;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;
import com.codestates.hobby.domain.notifcation.entity.Notification;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.post.entity.PostComment;
import com.codestates.hobby.domain.series.entity.Series;
import com.codestates.hobby.domain.showcase.entity.Showcase;
import com.codestates.hobby.domain.showcase.entity.ShowcaseComment;
import com.codestates.hobby.domain.subscription.entity.Subscription;

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

	@Column(nullable = false, unique = true)
	private String nickname;

	@Column(nullable = false)
	private String password;

	@Column
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
	private List<Subscription> subscriptions;

	@Enumerated(value = EnumType.STRING)
	@Column
	private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles = new ArrayList<>();

	@OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private FileInfo image;

	public Member(String email, String nickname, String password, String introduction, boolean isOauth2, String profileUrl, List<String> roles) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.introduction = introduction;
		this.isOauth2 = isOauth2;
		this.roles = roles;
		this.setImage(profileUrl);
	}

	public void edit(String nickname, String introduction, String profileUrl) {
		if(Optional.ofNullable(nickname).isPresent()) this.nickname = nickname;
		if(Optional.ofNullable(introduction).isPresent()) this.introduction = introduction;
		if(Optional.ofNullable(profileUrl).isPresent()) setImage(profileUrl);
	}

	public void setStatus(MemberStatus memberStatus) {
		this.memberStatus = memberStatus;
	}

	public void setImage(String url) {
		this.image = new FileInfo(this, url, 0);
	}

	public enum MemberStatus {
		MEMBER_ACTIVE("활동중"),
		MEMBER_QUIT("탈퇴 상태");

		@Getter
		private String status;

		MemberStatus(String status) {
			this.status = status;
		}
	}

	public enum MemberRole {
		ROLE_USER,
		ROLE_ADMIN
	}
}
