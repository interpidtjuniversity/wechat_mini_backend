package com.miniapp.wechat.wechat_mini_backend.frontend;

import lombok.Builder;

import java.sql.Timestamp;
import java.util.List;

@Builder
public class CourseDTO {

    private Long id;

    private String courseName;

    private String phase;

    private String grade;

    private Timestamp grabStart;

    private Timestamp grabEnd;

    private Timestamp releaseTime;

    private String ownerPhoneNumber;

    private String ownerNickName;

    private Integer capacity;

    private String address;

    private Integer balance;

    private String status;

    private List<String> content;

    private List<String> images;
}
