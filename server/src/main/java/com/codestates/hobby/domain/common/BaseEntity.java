package com.codestates.hobby.domain.common;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	@CreatedDate
	@ColumnDefault(value = "CURRENT_TIMESTAMP")
	@Column(nullable = false, updatable = false, columnDefinition = "timestamp")
	private LocalDateTime createdAt;

	@LastModifiedDate
	@ColumnDefault(value = "CURRENT_TIMESTAMP")
	@Column(nullable = false, columnDefinition = "timestamp")
	private LocalDateTime modifiedAt;
}
