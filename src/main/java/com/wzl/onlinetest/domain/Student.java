package com.wzl.onlinetest.domain;


import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "stuid")
    private String stuid;
    @Column(name = "password")
    private String password;
    @Column(name = "sname")
    private String sname;
    @Column(name = "sclass")
    private String sclass;
    @Column(name = "gender")
    private String gender;
    @Column(name = "status")
    private String status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Student() {
    }

    public Student(String stuid, String password, String sname, String sclass, String gender, String status) {
        this.stuid = stuid;
        this.password = password;
        this.sname = sname;
        this.sclass = sclass;
        this.gender = gender;
        this.status = status;
    }
}
