package com.wzl.onlinetest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/decorator")
public class DecoratorController {
    @RequestMapping("default")
    public String defaultDecorator() {
        return "/decorator/default";
    }
}