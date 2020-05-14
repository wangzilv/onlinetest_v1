package com.wzl.onlinetest.controller;

import com.alibaba.excel.util.StringUtils;
import com.wzl.onlinetest.constants.StaticDataConstants;
import com.wzl.onlinetest.domain.Chapter;
import com.wzl.onlinetest.domain.saveChapterInputParam;
import com.wzl.onlinetest.service.ChapterService;
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
@RequestMapping(value = "/ChapterManager")
public class ChapterController {

    private static final Logger logger = LoggerFactory.getLogger(ChapterController.class);

    @Resource
    ChapterService chapterService;

    @RequestMapping(value = "all" , produces = "application/json; charset=utf-8")
    public ModelAndView index() throws Exception{
        return new ModelAndView("chapterManager");
    }

    @RequestMapping(value = "/getAllChapterByPage")
    public Map<String, Object> getAllProblemByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size
                                            ) throws Exception{

        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.ASC,"id"));
        Page<Chapter> chapters = chapterService.findAllByStatus(pageable,StaticDataConstants.status.U);
        List<Chapter> data = chapters.getContent();
        Map<String,String> pager = new HashMap<>();
        pager.put("page",String.valueOf(chapters.getPageable().getOffset()));
        pager.put("recTotal",String.valueOf(chapters.getTotalElements()));
        pager.put("recPerPage",String.valueOf(chapters.getSize()));
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("data",data);
        responseMap.put("pager",pager);
        return responseMap;
    }
    @RequestMapping(value = "/getAllChapter")
    public List<Chapter> getAllProblem()throws Exception{
        return chapterService.findAllByStatus(StaticDataConstants.status.U);
    }


    @RequestMapping(value = "saveChapter",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String,Object> saveChapter(@RequestBody saveChapterInputParam param){
        boolean flag = false;
        Map<String, Object> map = new HashMap<>();
        List<Chapter> chapterList = param.getChapterList();
        for(Chapter chapter : chapterList) {
            String opertion = param.getOperation();
            logger.info("----------------调用新增修改删除章节信息方法----------------");
            if (null != chapter ) {
                logger.info("----------------开始查询章节信息----------------");
                Chapter localChapter = chapterService.findChapterByCidAndStatus(chapter.getCid(), StaticDataConstants.status.U);
                logger.info("----------------查询章节信息成功---------------cid=" + chapter.getCid() + "");
                if (null != localChapter&& null != chapter.getCid()) {
                    logger.info("----------------开始修改删除章节信息----------------");
                    if ("delete".equals(opertion)) {
                        localChapter.setStatus(StaticDataConstants.status.E);
                    }
                    if ("modify".equals(opertion)) {
                        localChapter.setCtitle(chapter.getCtitle());
                        localChapter.setCname(chapter.getCname());
                    }
                    localChapter.setUpdateTime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
                    flag = chapterService.save(localChapter);
                    logger.info("----------------修改删除章节信息成功----------------");
                } else {
                    if("insert".equals(opertion)){
                        logger.info("----------------开始新增除章节信息----------------");
                        String newcid = chapterService.findMaxCid();
                        if(StringUtils.isEmpty(newcid)){
                            newcid = "0";
                        }
                        Long cid = Long.valueOf(newcid)+1;
                        chapter.setCid(cid.toString());
                        chapter.setStatus(StaticDataConstants.status.U);
                        chapter.setCreateTime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
                        flag = chapterService.save(chapter);
                        logger.info("----------------开始新增除章节信息----------------");
                    }
                }
            }
        }
        if(flag){
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.SUCCESS);
            map.put(StaticDataConstants.resultMsg.MSG,"新增修改删除章节信息成功");
        }else{
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.FAIL);
            map.put(StaticDataConstants.resultMsg.MSG,"新增修改删除章节信息失败");
        }
        return map;
    }

}
