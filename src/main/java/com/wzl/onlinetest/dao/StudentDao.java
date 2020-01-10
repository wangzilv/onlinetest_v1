package com.wzl.onlinetest.dao;

import com.wzl.onlinetest.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student,Integer> {
    /**
     * 保存学生信息
     * @param student
     * @return
     */
    Student save(Student student);

    /**
     * 通过用户名查询学生信息
     * @param uid
     * @return
     */
    Student findStudentByUid(String uid);

    /**
     * 通过学号查询学生信息
     * @param stuId
     * @return
     */
    Student findStudentByStuid(String stuId);
}
