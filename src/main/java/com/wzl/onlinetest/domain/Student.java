package com.wzl.onlinetest.domain;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = -3420171618661766174L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "stuid")
    @NotNull(message = "请填写学号")
    @NotEmpty(message = "请填写学号")
    private String stuid;
    @Column(name = "uid")
    @NotNull(message = "请填写用户名")
    @NotEmpty(message = "请填写用户名")
    private String uid;
    @Column(name = "sname")
    private String sname;
    @Column(name = "sclass")
    private String sclass;
    @Column(name = "gender")
    private String gender;
    @Column(name = "status")
    private String status;
    @Column(name = "createtime")
    private String createtime;
    @Column(name = "updatetime")
    private String updatetime;


    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() { return uid; }

    public void setUid(String uid) { this.uid = uid; }

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

    public Student() {}

    public Student(String stuid, String uid, String sname, String sclass, String gender, String status, String createtime, String updatetime) {
        this.stuid = stuid;
        this.uid = uid;
        this.sname = sname;
        this.sclass = sclass;
        this.gender = gender;
        this.status = status;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }
}
