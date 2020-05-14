package com.wzl.onlinetest.service;

import com.wzl.onlinetest.dao.TestPaperDao;
import com.wzl.onlinetest.domain.TestPaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import java.util.List;


@Transactional(propagation= Propagation.REQUIRED)
@Service
public class TestPaperServiceImpl implements TestPaperService{

    @Autowired(required = true)
    TestPaperDao testPaperDao;

    @Override
    public Page<TestPaper> findAllByStatus(Pageable pageable, String status) {
        return testPaperDao.findAllByTeststatus(pageable,status);
    }

    @Override
    public boolean save(TestPaper testpaper) {
        boolean flag = false;
        if(null != testPaperDao.save(testpaper)){
            flag = true;
        }
        return flag;
    }

    @Override
    public List<TestPaperDao.TestPaperQuestion> findByTestid(String testid) {
        return testPaperDao.findByTestid(testid);
    }

    @Override
    public TestPaper findByTestidAndTeststatusAndTesttype(String testid, String status,String type) {
        return testPaperDao.findByTestidAndTeststatusAndTesttype(testid,status,type);
    }

    @Override
    public TestPaper findByTestidAndTeststatus(String testid, String status) {
        return testPaperDao.findByTestidAndTeststatus(testid,status);
    }


    @Override
    public String findMaxTestid() {
        return testPaperDao.findMaxTestid();
    }
}
