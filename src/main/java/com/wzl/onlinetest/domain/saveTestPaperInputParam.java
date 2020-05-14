package com.wzl.onlinetest.domain;

import java.util.List;

public class saveTestPaperInputParam {
    List<TestPaper>  testPaperList;
    String operation;

    public List<TestPaper> getTestPaperList() {
        return testPaperList;
    }

    public void setTestPaperList(List<TestPaper> testPaperList) {
        this.testPaperList = testPaperList;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
