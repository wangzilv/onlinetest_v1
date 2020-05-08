package com.wzl.onlinetest.domain;

import java.util.List;

public class saveProblemInputParam {
    List<ProblemSet> problemSetList;
    String operation;

    public List<ProblemSet> getProblemSetList() {
        return problemSetList;
    }

    public void setProblemSetList(List<ProblemSet> problemSetList) {
        this.problemSetList = problemSetList;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
