package com.wzl.onlinetest.dao;

import com.wzl.onlinetest.domain.TestPaper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestPaperDao extends JpaRepository<TestPaper,Integer> {

    /**
     * 分页查询所有试卷
     * @param status
     * @return
     */
    Page<TestPaper> findAllByTeststatus(Pageable pageable, String status);

    /**
     * 新增、修改、删除试卷
     * @param testpaper
     * @return
     */
    TestPaper save(TestPaper testpaper);

    /**
     * 查询单个试卷详细信息
     * @param testid
     * @return
     */
    @Query(value = "select \n" +
            "a.tk_questiontitle as qask,a.tk_questioncontent as qcont,a.tk_questionrighanswer as qanswer,a.tk_type as qtype,a.tk_questionscore as qscore \n" +
            "from tk_questions a,tk_test b,tk_test_questions c \n" +
            "where c.tk_testid = ?1 \n" +
            "and c.status = '1' \n" +
            "and a.tk_questionstatus = '1' \n" +
            "and b.tk_teststatus = '1' \n" +
            "and a.tk_questionid = c.tk_questionid \n" +
            "and b.tk_testid = c.tk_testid;"
            ,nativeQuery = true)
    List<TestPaperQuestion> findByTestid(String testid);

    public interface TestPaperQuestion{
        public String getQask();
        public String getQcont();
        public String getQanswer();
        public String getQtype();
        public String getQscore();


    }

    /**
     * 查询某个试卷
     * @param testid
     * @param status
     * @return
     */
    TestPaper findByTestidAndTeststatusAndTesttype(String testid,String status,String type);

    /**
     *
     * @param testid
     * @param status
     * @return
     */
    TestPaper findByTestidAndTeststatus(String testid,String status);
    /**
     * 获取最大testid
     * @return
     */
    @Query(value = "select MAX(CAST(tk_testid AS SIGNED)) from tk_test where tk_teststatus = '1'",nativeQuery = true)
    String findMaxTestid();


}
