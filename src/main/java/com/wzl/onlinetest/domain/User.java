package com.wzl.onlinetest.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.websocket.ClientEndpoint;
import java.io.Serializable;

@Entity
@Table(name = "tk_user")
public class User implements Serializable {
    private static final long serialVersionUID = 9056233261599354465L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tk_userid")
    @NotNull(message = "请填写用户名")
    @NotEmpty(message = "请填写用户名")
    private String uid;
    @Column(name = "tk_userpwd")
    @NotNull(message = "请填写密码")
    @NotEmpty(message = "请填写密码")
    private String password;
    @Column(name = "tk_username")
    private String username;
    @Column(name = "tk_usergender")
    private String gender;
    @Column(name = "tk_userclass")
    private String userclass;
    @Column(name = "tk_usertype")
    private String usertype;
    @Column(name = "tk_userstatus")
    private String status;
    private String createTime;
    @Column(name = "create_op_id")
    private String createOpId;
    @Column(name = "create_op_name")
    private String createOpName;
    @Column(name = "update_time")
    private String updateTime;
    @Column(name = "update_op_id")
    private String updateOpId;
    @Column(name = "update_op_name")
    private String updateOpName;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserclass() {
        return userclass;
    }

    public void setUserclass(String userclass) {
        this.userclass = userclass;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateOpId() {
        return createOpId;
    }

    public void setCreateOpId(String createOpId) {
        this.createOpId = createOpId;
    }

    public String getCreateOpName() {
        return createOpName;
    }

    public void setCreateOpName(String createOpName) {
        this.createOpName = createOpName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateOpId() {
        return updateOpId;
    }

    public void setUpdateOpId(String updateOpId) {
        this.updateOpId = updateOpId;
    }

    public String getUpdateOpName() {
        return updateOpName;
    }

    public void setUpdateOpName(String updateOpName) {
        this.updateOpName = updateOpName;
    }
}
