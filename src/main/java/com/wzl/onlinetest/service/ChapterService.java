package com.wzl.onlinetest.service;

import com.wzl.onlinetest.domain.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChapterService {
    Page<Chapter> findAllByStatus(Pageable pageable,String status);
    boolean save(Chapter chapter);
    Chapter findChapterByCidAndStatus(String cid,String status);
    String findMaxCid();
    List<Chapter> findAllByStatus(String status);
}
