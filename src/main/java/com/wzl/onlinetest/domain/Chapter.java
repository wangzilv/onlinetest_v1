package com.wzl.onlinetest.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "tk_chapters")
public class Chapter implements Serializable {

    private static final long serialVersionUID = -7854859326933895809L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tk_chapterid")
    private String cid;
    @Column(name = "tk_chaptertitle")
    private String ctitle;
    @Column(name = "tk_chaptername")
    private String cname;
    @Column(name = "tk_chapterstatus")
    private String status;
    @Column(name = "create_time")
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

    public Chapter() {
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
