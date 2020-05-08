package com.wzl.onlinetest.service;

import com.wzl.onlinetest.dao.ProblemSetDao;
import com.wzl.onlinetest.domain.ProblemSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation= Propagation.REQUIRED)
@Service
public class ProblemSetServiceImpl implements ProblemSetService {

    @Autowired(required = true)
    ProblemSetDao problemSetDao;

    @Override
    public Page<ProblemSetDao.ProblemChapter> findAllByStatusAndCidAndQaskAndQtypeAndQlevelOrderByIdAsc(String cid, String qask, String qtype, String qlevel, Pageable pageable) {
        return problemSetDao.findAllByStatusAndCidAndQaskAndQtypeAndQlevelOrderByIdAsc(cid,qask,qtype,qlevel,pageable);
    }

    @Override
    public ProblemSet findProblemSetByQidAndStatus(String qid,String status) {
        return problemSetDao.findProblemSetByQidAndStatus(qid,status);
    }

    @Override
    public boolean save(ProblemSet problemSet) {
        boolean flag = false;
        if(null != problemSetDao.save(problemSet)){
            flag = true;
        }
        return flag;
    }

    @Override
    public String findMaxQid() {
        return problemSetDao.findMaxQid();
    }


}
