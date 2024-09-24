package com.miniapp.wechat.wechat_mini_backend.service.dao;

import com.miniapp.wechat.wechat_mini_backend.dao.CourseInfoDao;
import com.miniapp.wechat.wechat_mini_backend.model.CourseInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseInfoServiceImpl implements CourseInfoService{

    @Resource
    private CourseInfoDao courseInfoDao;


    @Override
    public CourseInfo queryById(Long id) {
        return courseInfoDao.queryById(id);
    }

    @Override
    public List<CourseInfo> queryAll() {
        return courseInfoDao.queryAll();
    }

    @Override
    public List<CourseInfo> queryByPhase(String phase) {
        return courseInfoDao.queryByPhase(phase);
    }
}
