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
    public Student getStudent(){
        return studentDao.getStudent();
    }

    @Override
    public boolean saveStu(Student student){
        return studentDao.saveStu(student);
    }

    @Override
    public List<Student> findAll(){
        return studentDao.findAll();
    }

    @Override
    public boolean deleteStu(Student student) {
        return studentDao.deleteStu(student);
    }

    @Override
    public Student login(Student student) { return studentDao.login(student); }

}
