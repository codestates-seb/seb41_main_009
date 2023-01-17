package com.codestates.hobby.domain.member.entity;

import java.util.List;
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
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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
	@Setter
	private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "file_info_id", updatable = false)
	private FileInfo image;

	public Member(String email, String nickname, String password, String introduction, boolean isOauth2, String profileUrl) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.introduction = introduction;
		this.isOauth2 = isOauth2;
		FileInfo.createMemberImage(this, profileUrl);
	}

	public void edit(String nickname, String password, String introduction, String profileUrl) {
		if (!this.nickname.equals(nickname)) this.nickname = nickname;
		if (!this.password.equals(password)) this.password = password;
		if (!this.introduction.equals(introduction)) this.introduction = introduction;
		if (!this.image.getFileURL().equals(profileUrl)) FileInfo.createMemberImage(this, profileUrl);
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
}
