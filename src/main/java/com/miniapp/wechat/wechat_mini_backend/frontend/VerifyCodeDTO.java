package com.miniapp.wechat.wechat_mini_backend.frontend;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class VerifyCodeDTO {
    @Getter
    @Setter
    private String phoneNumber;

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
}
