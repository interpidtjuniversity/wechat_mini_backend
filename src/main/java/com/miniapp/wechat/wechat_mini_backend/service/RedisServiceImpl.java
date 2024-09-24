package com.miniapp.wechat.wechat_mini_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.Duration;

@Service
public class RedisServiceImpl implements RedisService {

    private static final String PHONE_NUMBER_PREFIX = "PHONE_NUMBER_PREFIX_";

    private static final String TOKEN_PREFIX = "TOKEN_PREFIX_";

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;


    @Override
    public boolean putPhoneNumber(String phoneNumber, Serializable data, Duration duration) {
        try {
            redisTemplate.opsForValue().set(PHONE_NUMBER_PREFIX + phoneNumber, data, duration);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Serializable getPhoneNumber(String phoneNumber) {
        try {
            return redisTemplate.opsForValue().get(PHONE_NUMBER_PREFIX + phoneNumber);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean putToken(String token, Serializable data, Duration duration) {
        try {
            redisTemplate.opsForValue().set(TOKEN_PREFIX + token, data, duration);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Serializable getToken(String token) {
        try {
            return redisTemplate.opsForValue().get(TOKEN_PREFIX + token);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updateTokenExpireTime(String token, Duration duration) {
        redisTemplate.expire(TOKEN_PREFIX + token, duration);
    }

}
