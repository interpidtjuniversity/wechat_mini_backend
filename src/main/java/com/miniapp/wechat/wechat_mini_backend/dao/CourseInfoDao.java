package com.miniapp.wechat.wechat_mini_backend.dao;

import com.miniapp.wechat.wechat_mini_backend.model.CourseInfo;

import java.util.List;

public interface CourseInfoDao {

    CourseInfo queryById(Long id);

    List<CourseInfo> queryAll();

    List<CourseInfo> queryByPhase(String phase);

}
