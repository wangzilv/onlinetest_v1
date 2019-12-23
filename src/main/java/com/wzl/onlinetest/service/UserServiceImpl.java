package com.wzl.onlinetest.service;

import com.wzl.onlinetest.dao.UserDao;
import com.wzl.onlinetest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED)
@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = true)
    UserDao userDao;
    @Override
    public User login(String uid) {
        return userDao.login(uid);
    }

    @Override
    public boolean register(User user) {
        return userDao.register(user);
    }
}
