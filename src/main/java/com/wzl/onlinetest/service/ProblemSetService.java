package com.wzl.onlinetest.service;

import com.wzl.onlinetest.dao.ProblemSetDao;
import com.wzl.onlinetest.domain.ProblemSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProblemSetService {
    Page<ProblemSetDao.ProblemChapter> findAllByStatusAndCidAndQaskAndQtypeAndQlevelOrderByIdAsc(String cid, String qask, String qtype, String qlevel, Pageable pageable);
    ProblemSet findProblemSetByQidAndStatus(String id,String status);
    boolean save(ProblemSet problemSet);
    String findMaxQid();
    ProblemSet findrandoffset(String type);
    int countAllByQtypeAndStatus(String type,String status);
}
