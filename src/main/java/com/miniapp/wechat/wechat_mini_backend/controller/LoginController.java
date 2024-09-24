package com.miniapp.wechat.wechat_mini_backend.controller;

import com.miniapp.wechat.wechat_mini_backend.convetor.FrontDTOConvertor;
import com.miniapp.wechat.wechat_mini_backend.frontend.LoginDTO;
import com.miniapp.wechat.wechat_mini_backend.frontend.VerifyCodeDTO;
import com.miniapp.wechat.wechat_mini_backend.service.LoginServiceImpl;
import com.miniapp.wechat.wechat_mini_backend.utils.PhoneNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private static final FrontDTOConvertor CONVERTOR = FrontDTOConvertor.INSTANCE;

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("/verifyCode")
    @ResponseBody
    public VerifyCodeDTO verifyCode(@RequestBody VerifyCodeDTO dto) {
        if (!PhoneNumberUtil.validatePhone(dto.getPhoneNumber())) {
            return VerifyCodeDTO.builder().message("手机号码不合法").build();
        }
        return CONVERTOR.verifyCodeDO2DTO(loginService.verifyCode(CONVERTOR.verifyCodeDTO2DO(dto)));
    }

    @PostMapping("/login")
    @ResponseBody
    public LoginDTO login(@RequestBody LoginDTO dto) {
        if (!PhoneNumberUtil.validatePhone(dto.getPhoneNumber())) {
            return LoginDTO.builder().message("手机号码不合法").build();
        }
        return CONVERTOR.loginDO2DTO(loginService.login(CONVERTOR.loginDTO2DO(dto)));
    }

}
