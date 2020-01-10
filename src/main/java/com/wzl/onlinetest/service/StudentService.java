package com.wzl.onlinetest.service;

import com.wzl.onlinetest.domain.Student;


public interface StudentService {
    boolean save(Student student);
    Student findStudentByUid(String uid);
    Student findStudentByStuid(String stuId);
}
