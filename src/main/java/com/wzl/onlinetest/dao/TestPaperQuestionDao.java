package com.wzl.onlinetest.dao;


import com.wzl.onlinetest.domain.TestPaperQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TestPaperQuestionDao extends JpaRepository<TestPaperQuestions,Integer>  {

    /**
     * 修改删除更新
     * @param testPaperQuestions
     * @return
     */
    TestPaperQuestions save(TestPaperQuestions testPaperQuestions);

    /**
     * 查找某个试卷下所有题目
     * @param testid
     * @param status
     * @return
     */
    List<TestPaperQuestions> findAllByTestidAndStatus(String testid,String status);
}
