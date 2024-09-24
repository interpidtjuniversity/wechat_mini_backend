package com.miniapp.wechat.wechat_mini_backend.interceptor;

import com.miniapp.wechat.wechat_mini_backend.config.WechatApp;
import com.miniapp.wechat.wechat_mini_backend.dto.LoginInfo;
import com.miniapp.wechat.wechat_mini_backend.service.RedisService;
import com.miniapp.wechat.wechat_mini_backend.utils.JwtUtil;
import com.miniapp.wechat.wechat_mini_backend.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;


@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Autowired
    private WechatApp wechatApp;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        // 没有携带token(非法请求), token被篡改, token不存在 => 用户未登陆或者登陆失效
        LoginInfo loginInfo;
        if (null == token) {
            // 没有携带token, 但是可能已经登陆(只是重复登陆)
            String requestURI = request.getRequestURI();
            if (requestURI.contains("/login/login")) {
                return true;
            }
            return false;
        } else {
            loginInfo = (LoginInfo) redisService.getToken(token);
            // token被篡改
            // token已经失效
            if (!JwtUtil.verify(token).getValid() || null == loginInfo) {
                return false;
            }
        }
        // 刷新token过期时间
        UserHolder.setValue(loginInfo);
        redisService.updateTokenExpireTime(token, Duration.ofSeconds(wechatApp.getLoginExpireTime()));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
