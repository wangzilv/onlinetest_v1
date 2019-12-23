package com.wzl.onlinetest.controller;

import com.wzl.onlinetest.domain.Student;
import com.wzl.onlinetest.service.StudentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/Student")
public class StudentController {
    @Resource
    StudentService studentService;
    @RequestMapping(value = "/GetInfo")
    public Student getInfo() throws Exception {
        return studentService.getStudent();
    }

}
