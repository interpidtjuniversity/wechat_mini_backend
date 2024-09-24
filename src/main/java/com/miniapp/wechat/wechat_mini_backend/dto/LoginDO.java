package com.miniapp.wechat.wechat_mini_backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
public class LoginDO implements Serializable {

    @Getter
    @Setter
    private String loginCode;

    @Getter
    @Setter
    private String verifyCode;

    @Getter
    @Setter
    private String phoneNumber;

    @Getter
    @Setter
    private String token;
}
