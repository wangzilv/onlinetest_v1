package com.wzl.onlinetest.controller;

import com.wzl.onlinetest.constants.StaticDataConstants;
import com.wzl.onlinetest.dao.ProblemSetDao;
import com.wzl.onlinetest.domain.ProblemSet;
import com.wzl.onlinetest.service.ChapterService;
import com.wzl.onlinetest.service.ProblemSetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/ProblemSet")
public class ProblemSetController {
    @Resource
    ProblemSetService problemSetService;
    @Resource
    ChapterService chapterService;


    @RequestMapping(value = "/all")
    public ModelAndView problemSetAll() throws Exception{
        return new ModelAndView("problemSet");
    }

    @RequestMapping(value = "/problemInfo")
    public ModelAndView towardsProblemInfo() throws Exception{
        return new ModelAndView("problemInfo");
    }
    @RequestMapping(value = "/getAllProblem")
    public Map<String, Object> getAllProblem(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                             @RequestParam(value = "cid", defaultValue = "") String cid,
                                             @RequestParam(value = "problem", defaultValue = "") String qask,
                                             @RequestParam(value = "difficulty", defaultValue = "") String qlevel,
                                             @RequestParam(value = "problemType", defaultValue = "") String qtype) throws Exception{

        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Direction.ASC,"id"));
        Page<ProblemSetDao.ProblemChapter> problemSet = problemSetService.findAllByStatusAndCidAndQaskAndQtypeAndQlevelOrderByIdAsc(cid,qask,qtype,qlevel,pageable);
        List<ProblemSetDao.ProblemChapter> data = problemSet.getContent();
        Map<String,String> pager = new HashMap<>();
        pager.put("page",String.valueOf(problemSet.getPageable().getOffset()));
        pager.put("recTotal",String.valueOf(problemSet.getTotalElements()));
        pager.put("recPerPage",String.valueOf(problemSet.getSize()));
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("data",data);
        responseMap.put("pager",pager);
        return responseMap;
    }
    @RequestMapping(value = "/getProblemInfo")
    public ProblemSet getProblemInfo(@RequestParam(value = "qid") String qid) throws Exception{
        ProblemSet problemSet = problemSetService.findProblemSetByQidAndStatus(qid,StaticDataConstants.status.U);
        return problemSet;
    }
}
