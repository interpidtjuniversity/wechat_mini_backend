package com.miniapp.wechat.wechat_mini_backend.utils;

import java.util.Random;

public class RandomCodeGenerator {

    private static final String codeChars = "0123456789";

    public static String generateSmsCode(int codeLength) {
        StringBuilder verificationCode = new StringBuilder();   // 使用StringBuilder来拼接验证码
        // 创建Random对象
        Random random = new Random();
        for (int i = 0; i < codeLength; i++) {  // 循环生成指定长度的验证码
            char randomChar = codeChars.charAt(random.nextInt(codeChars.length()));   // 从字符集中随机选择一个字符
            verificationCode.append(randomChar); // 将选定的字符追加到验证码中
        }
        return verificationCode.toString();
    }

}
