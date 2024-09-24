package com.miniapp.wechat.wechat_mini_backend.service;

import com.miniapp.wechat.wechat_mini_backend.dto.LoginDO;
import com.miniapp.wechat.wechat_mini_backend.dto.VerifyCodeDO;

public interface LoginService {

    VerifyCodeDO verifyCode(VerifyCodeDO baseDTO);

    LoginDO login(LoginDO loginDTO);
}
