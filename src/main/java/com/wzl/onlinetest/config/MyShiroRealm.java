package com.wzl.onlinetest.config;
import com.wzl.onlinetest.domain.User;
import com.wzl.onlinetest.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZHAO
 * @date 2019/1/8 18:22
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权用户权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取用户角色
        Set<String> roleSet = new HashSet<>();
        roleSet.add("100002");
        info.setRoles(roleSet);

        //获取用户权限
        Set<String> permissionSet = new HashSet<>();
        permissionSet.add("权限添加");
        permissionSet.add("权限删除");
        info.setStringPermissions(permissionSet);

        return info;
    }

    /**
     * 验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        User user = userService.findUserByUid(username);
        // 抛出异常，在登陆处LoginController处处理(subject.login()接收异常)
        if(user == null || !user.getPassword().equals(password)) {
            throw new UnknownAccountException("验证 未通过,用户名密码错误！");
        }
        User userInfo = new User();
        userInfo.setUid(user.getUid());
        userInfo.setPassword(user.getPassword());
        user.setStatus(user.getStatus());
        setSession("user",userInfo);
        return new SimpleAuthenticationInfo(user, password, getName());
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用,将用户存放到session中
     * @see  // 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser != null){
            Session session = currentUser.getSession();
            if(null != session){
                // 2小时
                session.setTimeout(7200000);
                session.setAttribute(key, value);
            }
        }
    }
}
