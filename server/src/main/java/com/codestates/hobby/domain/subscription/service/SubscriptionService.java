package com.codestates.hobby.domain.subscription.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codestates.hobby.domain.member.entity.Member;
import com.codestates.hobby.domain.post.entity.Post;
import com.codestates.hobby.domain.showcase.entity.Showcase;
import com.codestates.hobby.domain.subscription.entity.Subscription;

@Service
@Transactional(readOnly = true)
public class SubscriptionService {
	@Transactional
	public void subscribe(long memberId, long targetId) {

	}

	@Transactional
	public void cancel(long memberId, long targetId, long subscriptionId) {

	}

	public long countsForSubscriptions(long memberId) {
		// 사용자가 구독중인 회원수
		return 0;
	}

	public long countsForSubscribers(long memberId) {
		// 사용자를 구독하는 회원수
		return 0;
	}

	public Page<Subscription> findSubscriptions(long memberId, PageRequest pageRequest) {
		// 사용자가 구독중인 회원 목록
		return null;
	}

	public Page<Member> findSubscribers(long memberId, PageRequest pageRequest) {
		// 사용자를 구독하는 회원 목록
		return null;
	}

	// TODO: SORTING TYPE TO ENUM
	public Page<Post> findPostsBySubscription(PageRequest pageRequest, String sortingTYpe) {
		return null;
	}

	public Page<Showcase> findShowcasesBySubscription(PageRequest pageRequest, String sortingTYpe) {
		return null;
	}
}
