package com.miniapp.wechat.wechat_mini_backend.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CourseInfo implements Serializable {

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

    private String content;

    private String images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Timestamp getGrabStart() {
        return grabStart;
    }

    public void setGrabStart(Timestamp grabStart) {
        this.grabStart = grabStart;
    }

    public Timestamp getGrabEnd() {
        return grabEnd;
    }

    public void setGrabEnd(Timestamp grabEnd) {
        this.grabEnd = grabEnd;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getOwnerNickName() {
        return ownerNickName;
    }

    public void setOwnerNickName(String ownerNickName) {
        this.ownerNickName = ownerNickName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;

    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
