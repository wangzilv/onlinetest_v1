package com.wzl.onlinetest.dao;

import com.wzl.onlinetest.domain.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    //springboot会默认自动将数据源中的配置注入,用法与hibernate中sessionFactory生成的session类似。以后使用多数据源时会详细解释
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User login(String uid) {
        List<User> resultList = entityManager.createQuery("FROM User where uid = ?1 and status = '1'")
                .setParameter(1,uid)
                .setFirstResult(0)
                .getResultList();
        if (resultList.size() == 0) {
            return null;
        }
        return resultList.get(0);
    }

    @Override
    public boolean register(User user) {
        boolean flag=false;
        try {
            entityManager.persist(user);
            flag=true;
        }catch (Exception e){
            throw e;
        }
        return flag;
    }
}
