package com.wzl.onlinetest.service;

import com.wzl.onlinetest.domain.QUserStu;
import com.wzl.onlinetest.domain.User;


public interface UserService {
    User findUserByUid(String uid);
    boolean save(User user);
}
