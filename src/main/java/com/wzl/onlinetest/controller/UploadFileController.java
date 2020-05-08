package com.wzl.onlinetest.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.wzl.onlinetest.domain.ExcelModel;
import com.wzl.onlinetest.listener.UploadDataListener;
import com.wzl.onlinetest.service.ProblemSetService;
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

    @RequestMapping(value = "" , produces = "application/json; charset=utf-8")
    public ModelAndView index() throws Exception{
        return new ModelAndView("importBatchProblem");
    }
    /***
     * 上传文件 用@RequestParam注解来指定表单上的file为MultipartFile
     *
     * @param file
     * @return
     */
    @RequestMapping("fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                EasyExcel.read(file.getInputStream(), ExcelModel.class, new UploadDataListener(problemSetService)).sheet().headRowNumber(2).doRead();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "success";
    }
}
