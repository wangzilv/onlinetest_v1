package com.wzl.onlinetest.controller;

import com.wzl.onlinetest.constants.StaticDataConstants;
import com.wzl.onlinetest.domain.QUserStu;
import com.wzl.onlinetest.domain.Student;
import com.wzl.onlinetest.domain.User;
import com.wzl.onlinetest.service.StudentService;
import com.wzl.onlinetest.service.UserService;
import com.wzl.onlinetest.util.TimeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/User")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserService userService;
    @Resource
    StudentService studentService;
    @RequestMapping(value = "login")
    public ModelAndView towardsLogin() throws Exception{
        return new ModelAndView("common/login");
    }
    @RequestMapping(value = "register")
    public ModelAndView towardsRegister() throws Exception{
        return new ModelAndView("common/register");
    }
    @RequestMapping(value = "profile")
    public ModelAndView towardsProfile() throws Exception{
        return new ModelAndView("profile/profile");
    }
    @RequestMapping(value = "account")
    public ModelAndView towardsAccount() throws Exception{
        return new ModelAndView("profile/account");
    }
    @RequestMapping(value = "loginSubmit", method={RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, String> Login(User user){
        Map<String,String> map = new HashMap<>();
        String username = user.getUid();
        String password = user.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.SUCCESS);
            map.put(StaticDataConstants.resultMsg.MSG,"登录成功");
        } catch (UnknownAccountException e) {
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.FAIL);
            map.put(StaticDataConstants.resultMsg.MSG,"账号或密码错误");

        }finally {
            return map;
        }
    }


    @RequestMapping(value = "registerSubmit", produces="application/json;charset=utf-8")
    @ResponseBody
    public Map<String, String> Register(@RequestBody @Valid  QUserStu qUserStu){
        Map<String,String> map = new HashMap<>();
        User user = qUserStu.getUser();
        Student stu = qUserStu.getStudent();
        user.setCreatetime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
        user.setUpdatetime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
        stu.setCreatetime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
        stu.setUpdatetime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
        boolean flag1 = userService.save(user);
        boolean flag2 = studentService.save(stu);
        if(flag1&flag2){
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.SUCCESS);
        }else{
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.FAIL);
        }
        return map;
    }

    @RequestMapping(value = "logout", method={RequestMethod.POST, RequestMethod.GET})
    public void logout(HttpServletResponse response)throws Exception{
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        response.sendRedirect("/Test");
    }

    //获取用户信息
    @RequestMapping(value = {"getUserInfo","profile/account"} , method = RequestMethod.GET)
    public User getUserInfo(HttpServletRequest request){
        User userInfo = (User) SecurityUtils.getSubject().getPrincipal();
        return userInfo;
    }


    //修改用户信息
    @RequestMapping(value = "updateUserInfo" , produces="application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> updateUserInfo(@RequestBody @Valid User user){
        boolean flag = false;
        logger.info("----------------调用修改用户信息方法----------------");
        Map<String,Object> map = new HashMap<>();
        if(null != user) {
            logger.info("----------------开始查询用户信息----------------");
            User localUSer = userService.findUserByUid(user.getUid());
            logger.info("----------------查询用户信息成功---------------uid="+user.getUid()+"");
            if(null != localUSer) {
                logger.info("----------------开始修改用户信息----------------");
                localUSer.setUsername(user.getUsername());
                localUSer.setUpdatetime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
                flag = userService.save(localUSer);
                logger.info("----------------修改用户信息成功----------------");
            }
        }
        if(flag){
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.SUCCESS);
            map.put(StaticDataConstants.resultMsg.MSG,"修改用户信息成功");
        }else{
            map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.FAIL);
            map.put(StaticDataConstants.resultMsg.MSG,"修改用户信息失败");
        }
        return map;
    }
}
