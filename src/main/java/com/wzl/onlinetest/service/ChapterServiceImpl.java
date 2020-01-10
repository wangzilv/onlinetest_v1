package com.wzl.onlinetest.service;

import com.wzl.onlinetest.dao.ChapterDao;
import com.wzl.onlinetest.domain.Chapter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Chapter> findAllChapterByStatus(String status) {
        return chapterDao.findAllByStatus(status);
    }

}
