package com.wzl.onlinetest.dao;

import com.wzl.onlinetest.domain.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterDao extends JpaRepository<Chapter,Integer> {

    /**
     * 查询所有的章节
     * @return
     */
    List<Chapter> findAllByStatus(String status);
}
