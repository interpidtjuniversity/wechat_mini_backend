package com.miniapp.wechat.wechat_mini_backend.controller;

import com.miniapp.wechat.wechat_mini_backend.model.CourseInfo;
import com.miniapp.wechat.wechat_mini_backend.service.dao.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/custom")
public class CustomController {

    @Autowired
    private CourseInfoService courseInfoService;

    @RequestMapping("/courseList")
    @ResponseBody
    public List<CourseInfo> courseList() {
        List<CourseInfo> infos = courseInfoService.queryAll();
        return infos;
    }
}
