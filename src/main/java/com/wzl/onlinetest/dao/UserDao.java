package com.wzl.onlinetest.dao;

import com.wzl.onlinetest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,Integer> {
    /**
     * 通过uid查询用户信息
     * @param uid
     * @return
     */
    User findUserByUid(String uid);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    User save(User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */

//    @Query("")
//    User updateUser();
//    boolean register(User user);
//    QUserStu checkInput(QUserStu qUserStu);
}
