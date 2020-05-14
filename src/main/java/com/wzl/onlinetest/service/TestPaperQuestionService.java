package com.wzl.onlinetest.service;

import com.wzl.onlinetest.domain.TestPaperQuestions;

import java.util.List;

public interface TestPaperQuestionService {
    boolean save(TestPaperQuestions testPaperQuestions);
    List<TestPaperQuestions> findAllByTestidAndStatus(String testid, String status);
}
