package com.shaik.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ViralityService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void incrementScore(Long postId, String type) {

        String key = "post:" + postId + ":virality_score";

        int value = 0;

        if (type.equals("LIKE")) {
            value = 20;
        } else if (type.equals("COMMENT")) {
            value = 50;
        } else if (type.equals("BOT")) {
            value = 1;
        }

        redisTemplate.opsForValue().increment(key, value);
    }

    public boolean canBotReply(Long postId) {

        String key = "post:" + postId + ":bot_count";

        Long count = redisTemplate.opsForValue().increment(key);

        if (count > 100) {
            return false;
        }

        return true;
    }

    public boolean canBotReplyWithCooldown(Long postId, Long authorId) {

        String key = "cooldown:" + postId + ":" + authorId;

        Boolean exists = redisTemplate.hasKey(key);

        if (exists != null && exists) {
            return false;
        }

        redisTemplate.opsForValue().set(key, "1", java.time.Duration.ofMinutes(10));

        return true;
    }
}