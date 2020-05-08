package com.wzl.onlinetest.domain;

import java.util.List;

public class saveChapterInputParam {
    List<Chapter> chapterList;
    String operation;

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
