package com.wzl.onlinetest.controller;

import com.wzl.onlinetest.constants.StaticDataConstants;
import com.wzl.onlinetest.domain.User;
import com.wzl.onlinetest.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/User")
public class UserController {
    @Resource
    UserService userService;
    @RequestMapping(value = "/login")
    public ModelAndView toLogin() throws Exception{
        return new ModelAndView("login");
    }
    @RequestMapping(value = "/submit", method={RequestMethod.POST, RequestMethod.GET})
    public Map<String, String> Login(User user)throws Exception{
        Map<String,String> map = new HashMap<>();
        String username = user.getUid();
        String password = user.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            map.put("result","success");
            map.put("msg","登录成功");
        } catch (UnknownAccountException e) {
            map.put("result","fail");
            map.put("msg","账号或密码错误");
        }finally {
            return map;
        }
    }

//    @RequestMapping(value = "/signOut", method={RequestMethod.POST, RequestMethod.GET})
//    public Map<String, String> signOut(User user)throws Exception{
//        Map<String,Object> map = new HashMap<>();
//
//    }

    @RequestMapping(value = "/getUserInfo" , method = RequestMethod.GET)
    public Map<String,Object> getUserInfo(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        User userInfo = (User) SecurityUtils.getSubject().getPrincipal();
        if(null != userInfo){
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.SUCCESS);
            map.put(StaticDataConstants.resultMsg.DATA,userInfo);
        }else{
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.FAIL);
        }
        return map;
    }
}
