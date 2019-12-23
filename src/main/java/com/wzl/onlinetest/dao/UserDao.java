package com.wzl.onlinetest.dao;

import com.wzl.onlinetest.domain.User;

public interface UserDao {
    User login(String uid);
    boolean register(User user);
}
