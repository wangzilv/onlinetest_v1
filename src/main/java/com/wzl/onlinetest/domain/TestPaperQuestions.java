package com.wzl.onlinetest.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tk_test_questions")
public class TestPaperQuestions implements Serializable {
    private static final long serialVersionUID = -7492626312413279461L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tk_testid")
    private String testid;
    @Column(name = "tk_questionid")
    private String questionid;
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
    @Column(name = "status")
    private String status;

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
