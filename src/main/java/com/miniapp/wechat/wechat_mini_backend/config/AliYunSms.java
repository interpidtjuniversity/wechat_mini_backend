package com.miniapp.wechat.wechat_mini_backend.config;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.miniapp.wechat.wechat_mini_backend.support.AliYunSmsProperties;
import darabonba.core.client.ClientOverrideConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(value = AliYunSmsProperties.class)
@Configuration
public class AliYunSms implements InitializingBean {

    @Autowired
    private AliYunSmsProperties aliYunSmsProperties;

    private AsyncClient asyncClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(aliYunSmsProperties.getAccessKey())
                .accessKeySecret(aliYunSmsProperties.getAccessSecret())
                .build());

        asyncClient =  AsyncClient.builder()
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();
    }


    public String getTemplateCode() {
        return aliYunSmsProperties.getTemplateCode();
    }

    public String getSignName() {
        return aliYunSmsProperties.getSignName();
    }

    public AsyncClient getAsyncClient() {
        return asyncClient;
    }
}
