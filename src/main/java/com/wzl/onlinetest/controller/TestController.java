package com.wzl.onlinetest.controller;

import com.wzl.onlinetest.constants.StaticDataConstants;
import com.wzl.onlinetest.domain.Chapter;
import com.wzl.onlinetest.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    ChapterService chapterService;

    @RequestMapping(value = "Test" , produces = "application/json; charset=utf-8")
    public ModelAndView index() throws Exception{
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/getAllChapter")
    public List<Chapter> getAllProblem()throws Exception{
        return chapterService.findAllByStatus(StaticDataConstants.status.U);
    }

}
