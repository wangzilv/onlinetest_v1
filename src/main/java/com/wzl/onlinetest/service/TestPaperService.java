package com.wzl.onlinetest.service;

import com.wzl.onlinetest.dao.TestPaperDao;
import com.wzl.onlinetest.domain.TestPaper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TestPaperService {
    Page<TestPaper> findAllByStatus(Pageable pageable, String status);
    boolean save(TestPaper testpaper);
    List<TestPaperDao.TestPaperQuestion> findByTestid(String testid);
    TestPaper findByTestidAndTeststatusAndTesttype(String testid, String status,String type);
    TestPaper findByTestidAndTeststatus(String testid,String status);
    String findMaxTestid();
}
