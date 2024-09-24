package com.miniapp.wechat.wechat_mini_backend.support;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aliyunsms")
@Data
public class AliYunSmsProperties {

    @Getter
    @Setter
    private String accessKey;

    @Getter
    @Setter
    private String accessSecret;

    @Getter
    @Setter
    private String SignName;

    @Getter
    @Setter
    private String TemplateCode;
}
