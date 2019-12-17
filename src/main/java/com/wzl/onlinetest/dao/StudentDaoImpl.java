package com.wzl.onlinetest.dao;

import com.wzl.onlinetest.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    private final static Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);
    //springboot会默认自动将数据源中的配置注入,用法与hibernate中sessionFactory生成的session类似。以后使用多数据源时会详细解释
    @PersistenceContext
    EntityManager entityManager;

    /**
     *
     * @return
     */
    @Override
    public Student getStudent(){
        //编写jpql语句，进行执行回去所需数据
        List<Student> resultList = entityManager.createQuery("FROM Student")
                .setFirstResult(0)
                .setMaxResults(1)
                .getResultList();
        if (resultList.size() == 0) {
            return null;
        }
        return resultList.get(0);
    }

    @Override
    public boolean saveStu(Student student){
        boolean flag=false;
        try {
            entityManager.persist(student);
            flag=true;
        }catch (Exception e){
            throw e;
        }
        return flag;
    }

    @Override
    public List<Student> findAll(){
        List<Student> resultList = null;
        try{
            Query query = entityManager.createQuery("FROM Student order by SNAME DESC");
            resultList = query.getResultList();
        }catch (Exception e){
            throw e;
        }
        return resultList;
    }

    @Override
    public boolean deleteStu(Student student) {
        boolean flag = false;

        try{
            student.setStatus("0");
            entityManager.merge(student);
            flag = true;
        }catch (Exception e){
            throw e;
        }
        return flag;
    }

}
