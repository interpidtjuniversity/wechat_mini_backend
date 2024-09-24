package com.miniapp.wechat.wechat_mini_backend.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo implements Serializable {

    @Getter
    @Setter
    private String openId;

    @Getter
    @Setter
    private String sessionKey;

    @Getter
    @Setter
    private String phoneNumber;

    @Getter
    @Setter
    private Boolean isLogin;

    @Getter
    @Setter
    private Boolean valid;
}
