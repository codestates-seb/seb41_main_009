package com.codestates.hobby.domain.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import com.codestates.hobby.domain.common.BaseEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Certification extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, updatable = false)
	private String email;

	@Column(nullable = false, unique = true, updatable = false)
	private int code;

	@Column
	@ColumnDefault("0")
	private int attemptCount;

	public Certification(String email, int code) {
		this(email, code, 0);
	}

	public Certification(String email, int code, int attemptCount) {
		this.email = email;
		this.code = code;
		this.attemptCount = attemptCount;
	}
}