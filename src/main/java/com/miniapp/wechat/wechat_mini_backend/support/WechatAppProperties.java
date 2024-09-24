package com.miniapp.wechat.wechat_mini_backend.support;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wechat.app")
@Data
public class WechatAppProperties {

    @Getter
    @Setter
    private String appId;

    @Getter
    @Setter
    private String appSecret;

    @Setter
    @Getter
    private int verifyCodeExpireTime;

    @Setter
    @Getter
    private int loginExpireTime;
}
