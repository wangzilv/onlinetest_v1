package com.wzl.onlinetest.dao;

import com.wzl.onlinetest.domain.Student;

import java.util.List;

public interface StudentDao {
    Student getStudent();
    boolean saveStu(Student student);
    List<Student> findAll();
    boolean deleteStu(Student student);
}