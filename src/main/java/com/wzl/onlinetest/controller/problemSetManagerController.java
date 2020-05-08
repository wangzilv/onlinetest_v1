package com.wzl.onlinetest.controller;

import com.alibaba.excel.util.StringUtils;
import com.wzl.onlinetest.constants.StaticDataConstants;
import com.wzl.onlinetest.dao.ProblemSetDao;
import com.wzl.onlinetest.domain.ProblemSet;
import com.wzl.onlinetest.domain.saveProblemInputParam;
import com.wzl.onlinetest.service.ProblemSetService;
import com.wzl.onlinetest.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/ProblemSetManager")
public class problemSetManagerController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    ProblemSetService problemSetService;

    @RequestMapping(value = "/all")
    public ModelAndView problemSetAll() throws Exception{
        return new ModelAndView("problemSetManager");
    }

    @RequestMapping(value = "/problemInfo")
    public ModelAndView towardsProblemInfo() throws Exception{
        return new ModelAndView("problemInfo");
    }

    @RequestMapping(value = "/importBatchProblem" , produces = "application/json; charset=utf-8")
    public ModelAndView index() throws Exception{
        return new ModelAndView("/common/importBatchProblem");
    }

    @RequestMapping(value = "/getAllProblem")
    public Map<String, Object> getAllProblem(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                             @RequestParam(value = "cid", defaultValue = "") String cid,
                                             @RequestParam(value = "problem", defaultValue = "") String qask,
                                             @RequestParam(value = "difficulty", defaultValue = "") String qlevel,
                                             @RequestParam(value = "problemType", defaultValue = "") String qtype) throws Exception{

        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.ASC,"id"));
        Page<ProblemSetDao.ProblemChapter> problemSet = problemSetService.findAllByStatusAndCidAndQaskAndQtypeAndQlevelOrderByIdAsc(cid,qask,qlevel,qtype,pageable);
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

    @RequestMapping(value = "saveProblem",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String,Object> saveProblem(@RequestBody saveProblemInputParam param){
        boolean flag = false;
        Map<String, Object> map = new HashMap<>();
        List<ProblemSet> problemSetList = param.getProblemSetList();
        for(ProblemSet problemSet : problemSetList) {
            String opertion = param.getOperation();
            logger.info("----------------调用新增修改删除试题信息方法----------------");
            if (null != problemSet ) {
                logger.info("----------------开始查询试题信息----------------");
                ProblemSet localProblem = problemSetService.findProblemSetByQidAndStatus(problemSet.getQid(),StaticDataConstants.status.U);
                logger.info("----------------查询试题信息成功---------------cid=" + problemSet.getQid() + "");
                if (null != localProblem&& null != problemSet.getQid()) {
                    logger.info("----------------开始修改删除试题信息----------------");
                    if ("delete".equals(opertion)) {
                        localProblem.setStatus(StaticDataConstants.status.E);
                    }
                    if ("modify".equals(opertion)) {
                        localProblem.setQask(problemSet.getQask());
                        localProblem.setQcon(problemSet.getQcon());
                        localProblem.setQanswer(problemSet.getQanswer());
                        localProblem.setQscore(problemSet.getQscore());
                        localProblem.setQlevel(problemSet.getQlevel());
                        localProblem.setCid(problemSet.getCid());
                    }
                    localProblem.setUpdateTime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
                    flag = problemSetService.save(localProblem);
                    logger.info("----------------修改删除试题信息成功----------------");
                } else {
                    if("insert".equals(opertion)){
                        logger.info("----------------开始新增除试题信息----------------");
                        String newQid = problemSetService.findMaxQid();
                        if(StringUtils.isEmpty(newQid)){
                            newQid = "0";
                        }
                        Long qid = Long.valueOf(newQid)+1;
                        problemSet.setQid(qid.toString());
                        problemSet.setStatus(StaticDataConstants.status.U);
                        problemSet.setCreateTime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
                        flag = problemSetService.save(problemSet);
                        logger.info("----------------开始新增除试题信息----------------");
                    }
                }
            }
        }
        if(flag){
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.SUCCESS);
            map.put(StaticDataConstants.resultMsg.MSG,"新增修改删除试题信息成功");
        }else{
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.FAIL);
            map.put(StaticDataConstants.resultMsg.MSG,"新增修改删除试题信息失败");
        }
        return map;
    }

}
