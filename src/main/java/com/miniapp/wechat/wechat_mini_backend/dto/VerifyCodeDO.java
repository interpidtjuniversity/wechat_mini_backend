package com.miniapp.wechat.wechat_mini_backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class VerifyCodeDO {
    @Getter
    @Setter
    private String bizId;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private String requestId;

    @Getter
    @Setter
    private String phoneNumber;
}
