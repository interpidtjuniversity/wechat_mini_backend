package com.miniapp.wechat.wechat_mini_backend.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.miniapp.wechat.wechat_mini_backend.config.AliYunSms;
import com.miniapp.wechat.wechat_mini_backend.config.WechatApp;
import com.miniapp.wechat.wechat_mini_backend.dto.LoginDO;
import com.miniapp.wechat.wechat_mini_backend.dto.LoginInfo;
import com.miniapp.wechat.wechat_mini_backend.dto.VerifyCodeDO;
import com.miniapp.wechat.wechat_mini_backend.utils.JwtUtil;
import com.miniapp.wechat.wechat_mini_backend.utils.RandomCodeGenerator;
import com.miniapp.wechat.wechat_mini_backend.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class LoginServiceImpl implements LoginService{

    private static final String SESSION_URI = "https://api.weixin.qq.com/sns/jscode2session";

    private static final String PHONE_NUMBER_PREFIX = "PHONE_NUMBER_PREFIX_";

    private static final String TOKEN_PREFIX = "TOKEN_PREFIX_";

    @Autowired
    private RedisService redisService;

    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private AliYunSms aliYunSms;

    @Autowired
    private WechatApp wechatApp;

    public VerifyCodeDO verifyCode(VerifyCodeDO verifyCodeDO) {
        String code = RandomCodeGenerator.generateSmsCode(6);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("code", code);
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers(verifyCodeDO.getPhoneNumber())
                .templateCode(aliYunSms.getTemplateCode())
                .signName(aliYunSms.getSignName())
                .templateParam(JSONObject.toJSONString(paramMap))
                .build();
        CompletableFuture<SendSmsResponse> completableFuture = aliYunSms.getAsyncClient().sendSms(sendSmsRequest);
        try {
            SendSmsResponse response = completableFuture.get();
            // 短信发送成功, 放入缓存
            if (response.getStatusCode() == 200 && "OK".equals(response.getBody().getCode())) {
                redisService.putPhoneNumber(verifyCodeDO.getPhoneNumber(), code, Duration.ofSeconds(wechatApp.getVerifyCodeExpireTime()));
            }
            return VerifyCodeDO.builder()
                    .phoneNumber(verifyCodeDO.getPhoneNumber())
                    .code(response.getBody().getCode())
                    .bizId(response.getBody().getBizId())
                    .message(response.getBody().getMessage())
                    .requestId(response.getBody().getRequestId())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return VerifyCodeDO.builder().build();
        }
    }

    @Override
    public LoginDO login(LoginDO loginDO) {
        // 验证码已过期或者验证码错误, 请重新获取, 未设置token
        String verifyCode;
        LoginInfo loginInfo;
        if (null == (verifyCode = (String) redisService.getPhoneNumber(loginDO.getPhoneNumber())) || !verifyCode.equals(loginDO.getVerifyCode())) {
            return loginDO;
        }
        if (null != UserHolder.getValue()) {
            loginInfo = UserHolder.getValue();
        } else {
            loginInfo = buildLoginInfo(httpClientService.get(SESSION_URI, buildReqMap(loginDO)), loginDO);
        }
        if (null != loginInfo.getIsLogin() && loginInfo.getIsLogin()) {
            String token = JwtUtil.getToken(loginInfo);
            redisService.putToken(token, loginInfo, Duration.ofSeconds(wechatApp.getLoginExpireTime()));
            loginDO.setToken(token);
        }
        return loginDO;
    }

    private Map<String, String> buildReqMap(LoginDO dto) {
        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("js_code", dto.getLoginCode());
        reqMap.put("appid", wechatApp.getAppId());
        reqMap.put("secret", wechatApp.getAppSecret());
        reqMap.put("grant_type", "authorization_code");
        return reqMap;
    }

    private LoginInfo buildLoginInfo(Map<String, String> resMap, LoginDO loginDO) {
        LoginInfo loginInfo =  LoginInfo.builder()
                .openId(resMap.get("openid"))
                .sessionKey(resMap.get("session_key"))
                .phoneNumber(loginDO.getPhoneNumber())
                .build();
        if (null != loginInfo.getOpenId() && null != loginInfo.getSessionKey()) {
            loginInfo.setValid(Boolean.TRUE);
            loginInfo.setIsLogin(Boolean.TRUE);
        }
        return loginInfo;
    }
}
