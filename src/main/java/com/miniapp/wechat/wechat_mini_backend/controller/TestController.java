package com.miniapp.wechat.wechat_mini_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
