package com.wzl.onlinetest.service;

import com.wzl.onlinetest.dao.StudentDao;
import com.wzl.onlinetest.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation=Propagation.REQUIRED)
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired(required = true)
    StudentDao studentDao;


    @Override
    public boolean save(Student student) {
        boolean flag = false;
        if(null != studentDao.save(student)){
            flag = true;
        }
        return flag;
    }

    @Override
    public Student findStudentByUid(String uid) {
        return studentDao.findStudentByUid(uid);
    }

    @Override
    public Student findStudentByStuid(String stuId) {
        return studentDao.findStudentByStuid(stuId);
    }
}
