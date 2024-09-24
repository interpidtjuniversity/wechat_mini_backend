package com.miniapp.wechat.wechat_mini_backend.utils;

import com.miniapp.wechat.wechat_mini_backend.dto.LoginInfo;

public class UserHolder {
    public static final ThreadLocal<LoginInfo> userThreadLocal = new ThreadLocal<>();

    public static void setValue(LoginInfo loginInfo) {
        userThreadLocal.set(loginInfo);
    }

    public static LoginInfo getValue() {
        return userThreadLocal.get();
    }

    public static void clear() {
        userThreadLocal.remove();
    }
}
