package com.codestates.hobby.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PostValidationService {

    private static final String REDIS_KEY = "post";

    private final StringRedisTemplate stringRedisTemplate;

    public boolean isDuplicatedOrder() {
        return stringRedisTemplate.opsForValue().get(REDIS_KEY) != null;
    }

    public void save(Long memberId) {
        stringRedisTemplate.opsForValue().set(REDIS_KEY, String.valueOf(memberId), 1, TimeUnit.SECONDS);
    }
}