package com.wzl.onlinetest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/Test")
public class TestController {

    @RequestMapping(value = "" , method={RequestMethod.POST, RequestMethod.GET})
    public ModelAndView index() throws Exception{
        return new ModelAndView("index");
    }
}
