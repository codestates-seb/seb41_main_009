package com.codestates.hobby.domain.member.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.codestates.hobby.domain.common.BaseEntity;
import com.codestates.hobby.domain.fileInfo.entity.FileInfo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Enumerated(value = EnumType.STRING)
	@Column
	private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

	@ElementCollection(fetch = FetchType.LAZY)
	private List<String> roles = new ArrayList<>();

	@OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	private FileInfo image;

	public Member(String email, String nickname, String password, boolean isOauth2, List<String> roles) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.isOauth2 = isOauth2;
		this.roles = roles;
	}

	public void edit(String nickname, String introduction, String profileUrl) {
		if (Optional.ofNullable(nickname).isPresent())
			this.nickname = nickname;
		if (Optional.ofNullable(introduction).isPresent())
			this.introduction = introduction;
		if (Optional.ofNullable(profileUrl).isPresent()) {
			setImage(profileUrl);
		}
	}

	public void setStatus(MemberStatus memberStatus) {
		this.memberStatus = memberStatus;
	}

	public void setImage(String url) {
		this.image = new FileInfo(this, url, 0);
	}

	public void setUser(Member member) {
		this.id = member.getId();
		this.email = member.getEmail();
		this.password = member.getPassword();
		this.roles = member.getRoles();
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
