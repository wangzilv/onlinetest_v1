package com.wzl.onlinetest.domain;

import javax.persistence.*;

@Entity
@Table(name = "chapter")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cid")
    private String cid;
    @Column(name = "ctitle")
    private String ctitle;
    @Column(name = "cname")
    private String cname;
    @Column(name = "status")
    private String status;

    public Chapter() {
    }

    public Chapter(String cid, String ctitle, String cname, String status) {
        this.cid = cid;
        this.ctitle = ctitle;
        this.cname = cname;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCtitle() {
        return ctitle;
    }

    public void setCtitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
