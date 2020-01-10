package com.wzl.onlinetest.service;

import com.wzl.onlinetest.dao.UserDao;
import com.wzl.onlinetest.domain.User;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Transactional(propagation= Propagation.REQUIRED)
@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = true)
    UserDao userDao;
    @Override
    public User findUserByUid(String uid) {
        return userDao.findUserByUid(uid);
    }

    @Override
    public boolean save(User user){
        boolean flag = false;
        if(null != userDao.save(user)){
            flag = true;
        }
        return flag;
    }
}
