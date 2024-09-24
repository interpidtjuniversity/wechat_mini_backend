package com.miniapp.wechat.wechat_mini_backend.config;

import com.miniapp.wechat.wechat_mini_backend.support.WechatAppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(value = WechatAppProperties.class)
@Configuration
public class WechatApp {

    @Autowired
    private WechatAppProperties wechatAppProperties;

    public String getAppId() {
        return wechatAppProperties.getAppId();
    }

    public String getAppSecret() {
        return wechatAppProperties.getAppSecret();
    }

    public int getVerifyCodeExpireTime() {
        return wechatAppProperties.getVerifyCodeExpireTime();
    }

    public int getLoginExpireTime() {
        return wechatAppProperties.getLoginExpireTime();
    }
}
