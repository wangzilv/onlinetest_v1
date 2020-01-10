package com.wzl.onlinetest.controller;

import com.wzl.onlinetest.domain.Student;
import com.wzl.onlinetest.service.StudentService;
import com.wzl.onlinetest.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/Student")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    StudentService studentService;

    //获取学生信息
    @RequestMapping(value = "getStudentInfo" , produces="application/json;charset=utf-8")
    @ResponseBody
    public Student getStudentInfo(@RequestBody Student student){
        String uid = student.getUid();
        Student studentInfo = studentService.findStudentByUid(uid);
        return studentInfo;
    }

    //修改学生信息
    @RequestMapping(value = "updateStuInfo" , produces="application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> updateStuInfo(@RequestBody @Valid Student stu){
        logger.info("----------------调用修改学生信息方法----------------");
        Map<String,Object> map = new HashMap<>();
        if(null != stu) {
            logger.info("----------------开始查询学生信息----------------");
            Student localStu = studentService.findStudentByStuid(stu.getStuid());
            logger.info("----------------查询学生信息成功----------------stuid="+stu.getStuid()+"");
            if(null != localStu) {
                logger.info("----------------开始修改学生信息----------------");
                localStu.setSname(stu.getSname());
                localStu.setGender(stu.getGender());
                localStu.setSclass(stu.getSclass());
                localStu.setUpdatetime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
                studentService.save(localStu);
                logger.info("----------------修改学生信息成功----------------");
            }
        }
        return map;
    }


}
