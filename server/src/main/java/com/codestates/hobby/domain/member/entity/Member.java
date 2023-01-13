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

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

	@JsonManagedReference(value ="member")
	@Enumerated(value = EnumType.STRING)
	@Column
	@ColumnDefault("MEMBER_ACTIVE")
	@Setter
	private MemberStatus memberStatus;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "MEMBER_IMAGE",
		joinColumns = @JoinColumn(name = "member_id"),
		inverseJoinColumns = @JoinColumn(name = "file_info_id"), foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (file_info_id) references file_info ON DELETE CASCADE"),
		uniqueConstraints = @UniqueConstraint(name = "unq_member_profile", columnNames = "member_id"))
	private FileInfo fileInfo;

	public Member(String email, String nickname, String password, String introduction, boolean isOauth2, FileInfo fileInfo) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.introduction = introduction;
		this.isOauth2 = isOauth2;
		this.fileInfo = fileInfo;
	}

	public void edit(String nickname, String password, String introduction, FileInfo fileInfo) {
		this.nickname = nickname;
		this.password = password;
		this.introduction = introduction;
		this.fileInfo = fileInfo;
	}

	public enum MemberStatus {
		MEMBER_ACTIVE("활동중"),
		MEMBER_QUIT("탈퇴 상태"),
		MEMBER_LOGIN("로그인"),
		MEMBER_LOGOUT("로그아웃");

		@Getter
		private String status;

		MemberStatus(String status) {
			this.status = status;
		}
	}
}
