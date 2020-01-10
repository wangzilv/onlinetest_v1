package com.wzl.onlinetest.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class QUserStu {
    @Valid
    User user;
    @Valid
    Student student;

    public QUserStu() {
    }

    public QUserStu(User user, Student student) {
        this.user = user;
        this.student = student;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    //    @NotNull(message = "请填写用户名")
//    @NotEmpty(message = "请填写用户名")
//    private String uid;
//    @NotNull(message = "请填写密码")
//    @NotEmpty(message = "请填写密码")
//    private String password;
//    private String username;
//    private String status;
//    private String usertype;
//    @NotNull(message = "请填写学号")
//    @NotEmpty(message = "请填写学号")
//    private String stuid;
//    private String gender;
//    private String sclass;
//
//    public QUserStu() {
//    }
//
//    public QUserStu(String uid, String password, String username, String status, String usertype, String stuid, String gender, String sclass) {
//        this.uid = uid;
//        this.password = password;
//        this.username = username;
//        this.status = status;
//        this.usertype = usertype;
//        this.stuid = stuid;
//        this.gender = gender;
//        this.sclass = sclass;
//    }
//
//    public String getUid() {
//        return uid;
//    }
//
//    public void setUid(String uid) {
//        this.uid = uid;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getUsertype() {
//        return usertype;
//    }
//
//    public void setUsertype(String usertype) {
//        this.usertype = usertype;
//    }
//
//    public String getStuid() {
//        return stuid;
//    }
//
//    public void setStuid(String stuid) {
//        this.stuid = stuid;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getSclass() {
//        return sclass;
//    }
//
//    public void setSclass(String sclass) {
//        this.sclass = sclass;
//    }
}
