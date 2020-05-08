package com.wzl.onlinetest.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tk_questions")
public class ProblemSet implements Serializable {

    private static final long serialVersionUID = -997831771307213267L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tk_questionid")
    private String qid;
    @Column(name = "tk_questioncontent")
    private String qcon;
    @Column(name = "tk_questiontitle")
    private String qask;
    @Column(name = "tk_questionrighanswer")
    private String qanswer;
    @Column(name = "tk_questionscore")
    private String qscore;
    @Column(name = "tk_difficultylevel")
    private String qlevel;
    @Column(name = "tk_type")
    private String qtype;
    @Column(name = "tk_chapterid")
    private String cid;
    @Column(name = "tk_questionstatus")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQcon() {
        return qcon;
    }

    public void setQcon(String qcon) {
        this.qcon = qcon;
    }

    public String getQask() {
        return qask;
    }

    public void setQask(String qask) {
        this.qask = qask;
    }

    public String getQanswer() {
        return qanswer;
    }

    public void setQanswer(String qanswer) {
        this.qanswer = qanswer;
    }

    public String getQscore() {
        return qscore;
    }

    public void setQscore(String qscore) {
        this.qscore = qscore;
    }

    public String getQlevel() {
        return qlevel;
    }

    public void setQlevel(String qlevel) {
        this.qlevel = qlevel;
    }

    public String getQtype() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype = qtype;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
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
