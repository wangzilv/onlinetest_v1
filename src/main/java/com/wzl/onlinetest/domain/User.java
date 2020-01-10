package com.wzl.onlinetest.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 9056233261599354465L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "uid")
    @NotNull(message = "请填写用户名")
    @NotEmpty(message = "请填写用户名")
    private String uid;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    @NotNull(message = "请填写密码")
    @NotEmpty(message = "请填写密码")
    private String password;
    @Column(name = "status")
    private String status;
    @Column(name = "usertype")
    private String usertype;
    @Column(name = "createtime")
    private String createtime;
    @Column(name = "updatetime")
    private String updatetime;

    public User() {
    }

    public User(String uid, String username, String password, String status, String usertype, String createtime, String updatetime) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.status = status;
        this.usertype = usertype;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
