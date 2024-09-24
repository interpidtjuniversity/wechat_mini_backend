package com.miniapp.wechat.wechat_mini_backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.miniapp.wechat.wechat_mini_backend.dto.LoginInfo;

public class JwtUtil {

    // 使用固定的解密秘钥
    private static final String SECRET = "TOKEN_SECRET";

    public static String getToken(LoginInfo loginInfo) {
        try{
            //用秘钥生成签名
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //默认头部+载荷（手机号/id）+签名=jwt
            String jwtToken= JWT.create()
                    .withClaim("phoneNumber", loginInfo.getPhoneNumber())
                    .withClaim("openId", loginInfo.getOpenId())
                    .withClaim("sessionKey", loginInfo.getSessionKey())
                    .withClaim("isLogin", loginInfo.getIsLogin())
                    .withClaim("valid", loginInfo.getValid())
                    .sign(algorithm);
            return jwtToken;
        }catch (Exception e){
            return null;
        }
    }

    public static LoginInfo verify(String token) {
        try {
            // 根据用户信息userInfo生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            // 效验TOKEN
            verifier.verify(token);
            //返回token内容
            return getTokenInfo(token);
        } catch (Exception exception) {
            return LoginInfo.builder().valid(Boolean.FALSE).build();
        }
    }

    private static LoginInfo getTokenInfo(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            LoginInfo loginInfo= LoginInfo.builder()
                    .phoneNumber(jwt.getClaim("phoneNumber").asString())
                    .openId(jwt.getClaim("openId").asString())
                    .sessionKey(jwt.getClaim("sessionKey").asString())
                    .isLogin(jwt.getClaim("isLogin").asBoolean())
                    .valid(jwt.getClaim("valid").asBoolean())
                    .build();
            return loginInfo;
        } catch (JWTDecodeException e) {
            return LoginInfo.builder().valid(Boolean.FALSE).build();
        }
    }
}

