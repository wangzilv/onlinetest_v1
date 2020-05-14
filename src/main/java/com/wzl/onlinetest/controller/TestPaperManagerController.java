package com.wzl.onlinetest.controller;

import com.alibaba.excel.util.StringUtils;
import com.wzl.onlinetest.constants.StaticDataConstants;
import com.wzl.onlinetest.dao.ProblemSetDao;
import com.wzl.onlinetest.dao.TestPaperDao;
import com.wzl.onlinetest.domain.*;
import com.wzl.onlinetest.service.ProblemSetService;
import com.wzl.onlinetest.service.TestPaperQuestionService;
import com.wzl.onlinetest.service.TestPaperService;
import com.wzl.onlinetest.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping(value = "/TestPaperManager")
public class TestPaperManagerController {

    private static final Logger logger = LoggerFactory.getLogger(TestPaperManagerController.class);

    @Resource
    TestPaperService testPaperService;
    @Resource
    ProblemSetService problemSetService;
    @Resource
    TestPaperQuestionService testPaperQuestionService;

    @RequestMapping(value = "/all")
    public ModelAndView testPaperAll() throws Exception{
        return new ModelAndView("testPaperManager");
    }

    @RequestMapping(value = "/testPaperInfo")
    public ModelAndView towardsTestPaperInfo() throws Exception{
        return new ModelAndView("testPaperInfo");
    }
    @RequestMapping(value = "/importBatchTestPaper" , produces = "application/json; charset=utf-8")
    public ModelAndView index() throws Exception{
        return new ModelAndView("/common/importBatchTestPaper");
    }

    @RequestMapping(value = "/getAllTestPaper")
    public Map<String, Object> getAllTestPaper(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size)throws Exception{
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.ASC,"id"));
        Page<TestPaper> testPapers = testPaperService.findAllByStatus(pageable,StaticDataConstants.status.U);
        List<TestPaper> data = testPapers.getContent();
        Map<String,String> pager = new HashMap<>();
        pager.put("page",String.valueOf(testPapers.getPageable().getOffset()));
        pager.put("recTotal",String.valueOf(testPapers.getTotalElements()));
        pager.put("recPerPage",String.valueOf(testPapers.getSize()));
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("data",data);
        responseMap.put("pager",pager);
        return responseMap;
    }

    @RequestMapping(value = "/getTestPaperInfo")
    public Map<String,Object> getTestPaperInfo(@RequestParam(value = "testid") String testid,
                                               @RequestParam(value = "type") String type) throws Exception{
        Map<String, Object> map = new HashMap<>();
        TestPaper testPaper = testPaperService.findByTestidAndTeststatusAndTesttype(testid,StaticDataConstants.status.U,type);
        map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.SUCCESS);
        map.put("testPaper",testPaper);
        if(StaticDataConstants.testPaperType.AutoTestPaper.equals(type)){
            List<TestPaperDao.TestPaperQuestion> testPaperQuestionList = testPaperService.findByTestid(testid);
            map.put("problemSetList",testPaperQuestionList);
        }
        return map;
    }


    @RequestMapping(value = "/saveTestPaper",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String,Object> saveTestPaper(@RequestBody saveTestPaperInputParam param){
        boolean flag = false;
        Map<String, Object> map = new HashMap<>();
        List<TestPaper> testPaperList = param.getTestPaperList();
        for(TestPaper testPaper : testPaperList) {
            String opertion = param.getOperation();
            logger.info("----------------调用新增修改删除试卷信息方法----------------");
            if (null != testPaper ) {
                logger.info("----------------开始查询试卷信息----------------");
                TestPaper localTestPaper = testPaperService.findByTestidAndTeststatus(testPaper.getTestid(), StaticDataConstants.status.U);
                logger.info("----------------查询试卷信息成功---------------cid=" + testPaper.getTestid() + "");
                if (null != localTestPaper&& null != testPaper.getTestid()) {
                    logger.info("----------------开始修改删除试卷信息----------------");
                    if ("delete".equals(opertion)) {
                        localTestPaper.setTeststatus(StaticDataConstants.status.E);
                        List<TestPaperQuestions> list = testPaperQuestionService.findAllByTestidAndStatus(localTestPaper.getTestid(), StaticDataConstants.status.U);
                        for (TestPaperQuestions testPaperQuestion : list) {
                            testPaperQuestion.setStatus(StaticDataConstants.status.E);
                            testPaperQuestion.setUpdateTime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
                            testPaperQuestionService.save(testPaperQuestion);
                        }
                    }
                    if ("modify".equals(opertion)) {
                        localTestPaper.setTesttitle(testPaper.getTesttitle());
                        localTestPaper.setTestremark(testPaper.getTestremark());
                    }
                    localTestPaper.setUpdateTime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
                    flag = testPaperService.save(localTestPaper);
                    logger.info("----------------修改删除试卷信息成功----------------");
                } else {
                    if("insert".equals(opertion)){
                        logger.info("----------------开始新增除试卷信息----------------");
                        String newtestid = testPaperService.findMaxTestid();
                        if(StringUtils.isEmpty(newtestid)){
                            newtestid = "0";
                        }
                        Long testid = Long.valueOf(newtestid)+1;
                        localTestPaper.setTestid(testid.toString());
                        localTestPaper.setTeststatus(StaticDataConstants.status.U);
                        localTestPaper.setCreateTime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
                        flag = testPaperService.save(localTestPaper);
                        logger.info("----------------开始新增除试卷信息----------------");
                    }
                }
            }
        }
        if(flag){
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.SUCCESS);
            map.put(StaticDataConstants.resultMsg.MSG,"新增修改删除试卷信息成功");
        }else{
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.FAIL);
            map.put(StaticDataConstants.resultMsg.MSG,"新增修改删除试卷信息失败");
        }
        return map;
    }
    @RequestMapping(value = "/autoSaveTestPaper",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String,Object> autoSaveTestPaper(@RequestParam(value = "singlechose", defaultValue = "20") Integer singlechose,
                                                @RequestParam(value = "multichose", defaultValue = "0") Integer multichose,
                                                @RequestParam(value = "judgement", defaultValue = "10") Integer judgement,
                                                @RequestParam(value = "completion", defaultValue = "20") Integer completion,
                                                @RequestParam(value = "code", defaultValue = "40") Integer code,
                                                @RequestParam(value = "correction", defaultValue = "10") Integer correction)throws Exception{


        //固定每个单选题的分值为2分
        //多选题为4分
        //判断题为2分
        //改错题为5分
        //填空题为2分
        //编程题为20分

        boolean flag = false;boolean flag1 = false;
        Map<String, Object> map = new HashMap<>();

        String newtestid = testPaperService.findMaxTestid();
        if(StringUtils.isEmpty(newtestid)){
            newtestid = "0";
        }
        Long testid = Long.valueOf(newtestid)+1;
        //每次扣掉上一道题的分数再随机取一个题
        logger.info("自动组卷------单选题---------开始");
        flag = flag || selectQuestionFromDatabase(singlechose,"1", testid.toString());
        logger.info("自动组卷------单选题---------结束");
        logger.info("自动组卷------多选题---------开始");
        flag = flag || selectQuestionFromDatabase(multichose,"2", testid.toString());
        logger.info("自动组卷------多选题---------结束");
        logger.info("自动组卷------判断题---------开始");
        flag = flag || selectQuestionFromDatabase(judgement,"3",testid.toString());
        logger.info("自动组卷------判断题---------结束");
        logger.info("自动组卷------填空题---------开始");
        flag = flag || selectQuestionFromDatabase(completion,"4",testid.toString());
        logger.info("自动组卷------填空题---------结束");
        logger.info("自动组卷------编程题---------开始");
        flag = flag || selectQuestionFromDatabase(code,"5",testid.toString());
        logger.info("自动组卷------编程题---------结束");
        logger.info("自动组卷------改错题---------开始");
        flag = flag || selectQuestionFromDatabase(correction,"6",testid.toString());
        logger.info("自动组卷------改错题---------结束");


        TestPaper testPaper = new TestPaper();
        testPaper.setTestid(testid.toString());
        testPaper.setTesttitle("自动组卷"+testid);
        testPaper.setTestremark("自动组卷测试"+testid);
        testPaper.setTesttype(StaticDataConstants.testPaperType.AutoTestPaper);
        testPaper.setTeststatus(StaticDataConstants.status.U);
        testPaper.setCreateTime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
        flag1 = testPaperService.save(testPaper);
        if(flag&&flag1){
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.SUCCESS);
            map.put(StaticDataConstants.resultMsg.MSG,"自动组卷成功");
        }else{
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.FAIL);
            map.put(StaticDataConstants.resultMsg.MSG,"自动组卷失败");
        }
        return map;
    }

    private boolean selectQuestionFromDatabase(Integer score,String type,String testid){
        boolean flag = false; boolean flag1 = false;
        if(score!=0 && !StringUtils.isEmpty(type) && !StringUtils.isEmpty(testid)) {
            Set<String> problemSets = new HashSet<>();
            int count = problemSetService.countAllByQtypeAndStatus(type,StaticDataConstants.status.U);
            while (score > 0) {
                ProblemSet problemSet = problemSetService.findrandoffset(type);
                String qid = problemSet.getQid();
                if (!problemSets.contains(qid)) {
                    Integer qscore = Integer.valueOf(problemSet.getQscore());
                    score = score - qscore;
                    problemSets.add(qid);
                    TestPaperQuestions testPaperQuestions = new TestPaperQuestions();
                    testPaperQuestions.setTestid(testid);
                    testPaperQuestions.setQuestionid(qid);
                    testPaperQuestions.setStatus(StaticDataConstants.status.U);
                    testPaperQuestions.setCreateTime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
                    flag = testPaperQuestionService.save(testPaperQuestions);
                }else {
                    if(count == problemSets.size()){
                        break;
                    }
                }
            }
        }
        return flag;
    }
}
