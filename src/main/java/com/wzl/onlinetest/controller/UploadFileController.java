package com.wzl.onlinetest.controller;

import com.alibaba.excel.EasyExcel;
import com.wzl.onlinetest.domain.ProblemExcelModel;
import com.wzl.onlinetest.domain.TestPaperExcelModel;
import com.wzl.onlinetest.listener.UploadProblemListener;
import com.wzl.onlinetest.listener.UploadTestPaperListener;
import com.wzl.onlinetest.service.ProblemSetService;
import com.wzl.onlinetest.service.TestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/uploadFile")
public class UploadFileController {
    //通过Spring的autowired注解获取spring默认配置的request
    @Autowired
    private HttpServletRequest request;
    @Resource
    ProblemSetService problemSetService;
    @Resource
    TestPaperService testPaperService;

    @RequestMapping(value = "" , produces = "application/json; charset=utf-8")
    public ModelAndView index() throws Exception{
        return new ModelAndView("importBatchProblem");
    }
    /***
     * 批量上传试题 用@RequestParam注解来指定表单上的file为MultipartFile
     *
     * @param file
     * @return
     */
    @RequestMapping("batchProblemUpload")
    public String batchProblemUpload(@RequestParam("file") MultipartFile file) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                EasyExcel.read(file.getInputStream(), ProblemExcelModel.class, new UploadProblemListener(problemSetService)).sheet().headRowNumber(2).doRead();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "success";
    }
    /***
     * 批量上传试卷 用@RequestParam注解来指定表单上的file为MultipartFile
     *
     * @param file
     * @return
     */
    @RequestMapping("batchTestPaperUpload")
    public String batchTestPaperUpload(@RequestParam("file") MultipartFile file) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                EasyExcel.read(file.getInputStream(), TestPaperExcelModel.class, new UploadTestPaperListener(testPaperService)).sheet().headRowNumber(1).doRead();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "success";
    }
}
