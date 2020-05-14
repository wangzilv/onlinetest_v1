package com.wzl.onlinetest.service;

import com.wzl.onlinetest.dao.TestPaperQuestionDao;
import com.wzl.onlinetest.domain.TestPaperQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation= Propagation.REQUIRED)
@Service
public class TestPaperQuestionServiceImpl implements TestPaperQuestionService{

    @Autowired(required = true)
    TestPaperQuestionDao testPaperQuestionDao;

    @Override
    public boolean save(TestPaperQuestions testPaperQuestions) {
        boolean flag = false;
        if(null != testPaperQuestionDao.save(testPaperQuestions)){
            flag = true;
        }
        return flag;
    }

    @Override
    public List<TestPaperQuestions> findAllByTestidAndStatus(String testid, String status) {
        return testPaperQuestionDao.findAllByTestidAndStatus(testid,status);
    }

}
