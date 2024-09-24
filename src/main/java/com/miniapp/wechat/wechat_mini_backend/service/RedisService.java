package com.miniapp.wechat.wechat_mini_backend.service;

import java.io.Serializable;
import java.time.Duration;

public interface RedisService {

    boolean putPhoneNumber(String phoneNumber, Serializable data, Duration duration);

    Serializable getPhoneNumber(String phoneNumber);

    boolean putToken(String token, Serializable data, Duration duration);

    Serializable getToken(String token);

    void updateTokenExpireTime(String token, Duration duration);
}
