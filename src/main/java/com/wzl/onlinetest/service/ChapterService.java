package com.wzl.onlinetest.service;

import com.wzl.onlinetest.domain.Chapter;

import java.util.List;

public interface ChapterService {
    List<Chapter> findAllChapterByStatus(String status);
}
