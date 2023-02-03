package com.codestates.hobby.domain.auth.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.RandomUtils;

import com.codestates.hobby.domain.common.BaseEntity;
import com.codestates.hobby.global.exception.BusinessLogicException;
import com.codestates.hobby.global.exception.ExceptionCode;

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

	@Column(nullable = false, columnDefinition = "int(11)")
	private int code;

	@Column(nullable = false, columnDefinition = "tinyint")
	private int attemptCount;

	@Column(nullable = false)
	private boolean isCertified;

	public Certification(String email) {
		this.email = email;
	}

	public void initCode() {
		this.code = RandomUtils.nextInt(0, 100_000_000);
		this.attemptCount++;
	}

	public boolean matches(int code) {
		if (this.code != code)
			return false;

		return isCertified = true;
	}

	public void validate() {
		LocalDateTime fiveMinAgo = LocalDateTime.now().minusMinutes(5);

		if (getModifiedAt().isBefore(fiveMinAgo))
			throw new BusinessLogicException(ExceptionCode.CODE_EXPIRATION);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Certification))
			return false;

		return Objects.equals(getId(), ((Certification)o).getId());
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}