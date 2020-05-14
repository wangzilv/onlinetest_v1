package com.wzl.onlinetest.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tk_test")
public class TestPaper implements Serializable {
    private static final long serialVersionUID = -6283740176645548612L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tk_testid")
    private String testid;
    @Column(name = "tk_testtitle")
    private String testtitle;
    @Column(name = "tk_testdescription")
    private String testdesc;
    @Column(name = "tk_testremark")
    private String testremark;
    @Column(name = "tk_testtype")
    private String testtype;
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
    @Column(name = "tk_teststatus")
    private String teststatus;

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public String getTesttitle() {
        return testtitle;
    }

    public void setTesttitle(String testtitle) {
        this.testtitle = testtitle;
    }

    public String getTestdesc() {
        return testdesc;
    }

    public void setTestdesc(String testdesc) {
        this.testdesc = testdesc;
    }

    public String getTestremark() {
        return testremark;
    }

    public void setTestremark(String testremark) {
        this.testremark = testremark;
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

    public String getTeststatus() {
        return teststatus;
    }

    public void setTeststatus(String teststatus) {
        this.teststatus = teststatus;
    }

    public String getTesttype() {
        return testtype;
    }

    public void setTesttype(String testtype) {
        this.testtype = testtype;
    }
}
