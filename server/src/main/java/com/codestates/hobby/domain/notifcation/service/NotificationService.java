package com.codestates.hobby.domain.notifcation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codestates.hobby.domain.notifcation.entity.Notification;

@Service
@Transactional(readOnly = true)
public class NotificationService {
	public long countByMember(long memberId) {
		return 0;
	}

	public Notification findById(long notificationId) {
		return null;
	}

	public Page<Notification> findAllByMember(long memberId, PageRequest pageRequest) {
		return null;
	}

	@Transactional
	public void delete(long memberId, long notificationId) {

	}
}
