package com.wzl.onlinetest.service;

import com.wzl.onlinetest.domain.Student;

import java.util.List;

public interface StudentService {
    Student getStudent();
    boolean saveStu(Student student);
    List<Student> findAll();
    boolean deleteStu(Student student);
}
