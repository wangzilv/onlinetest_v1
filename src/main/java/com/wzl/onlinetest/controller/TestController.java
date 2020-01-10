package com.wzl.onlinetest.controller;

import com.wzl.onlinetest.domain.Chapter;
import com.wzl.onlinetest.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class TestController {

    @Resource
    ChapterService chapterService;

    @RequestMapping(value = "Test" , method={RequestMethod.POST, RequestMethod.GET})
    public ModelAndView index() throws Exception{
        return new ModelAndView("index");
    }

    @RequestMapping(value = "getAllChapter")
    public List<Chapter> getAllChapter() throws Exception{
        return chapterService.findAllChapterByStatus("1");
    }
}
