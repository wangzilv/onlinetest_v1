package com.wzl.onlinetest.service;

import com.wzl.onlinetest.dao.ChapterDao;
import com.wzl.onlinetest.domain.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation= Propagation.REQUIRED)
@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired(required = true)
    ChapterDao chapterDao;

    @Override
    public Page<Chapter> findAllByStatus(Pageable pageable, String status) {
        return chapterDao.findAllByStatus(pageable,status);
    }
    @Override
    public List<Chapter> findAllByStatus(String status){
        return chapterDao.findAllByStatus(status);
    }
    @Override
    public boolean save(Chapter chapter){
        boolean flag = false;
        if(null != chapterDao.save(chapter)){
            flag = true;
        }
        return flag;
    }

    @Override
    public Chapter findChapterByCidAndStatus(String cid, String status) {
        return chapterDao.findChapterByCidAndStatus(cid,status);
    }

    @Override
    public String findMaxCid() {
        return chapterDao.findMaxCid();
    }

}
