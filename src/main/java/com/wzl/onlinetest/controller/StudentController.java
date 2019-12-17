package com.wzl.onlinetest.controller;

import com.wzl.onlinetest.domain.Student;
import com.wzl.onlinetest.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/Student")
public class StudentController {
    @Resource
    StudentService studentService;
    @RequestMapping(value="/login")
    public ModelAndView login() throws Exception{
        return new ModelAndView("login");
    }
    @RequestMapping(value = "/GetInfo")
    public Student getInfo() throws Exception {
        return studentService.getStudent();
    }
    @RequestMapping(value = "/saveStu")
    public String saveStu() throws Exception{
        Student stu = new Student();
        stu.setStuid("0314011602607");
        stu.setPassword("123456");
        stu.setSname("wzl");
        stu.setStatus("1");
        stu.setSclass("1626");
        stu.setGender("male");
        boolean flag = studentService.saveStu(stu);
        if(flag){
            return "success";
        }else{
            return "fail";
        }
    }

}
