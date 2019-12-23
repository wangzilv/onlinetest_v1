package com.wzl.onlinetest.service;

import com.wzl.onlinetest.domain.User;


public interface UserService {
    User login(String uid);
    boolean register(User user);
}
