package com.wzl.onlinetest.dao;

import com.wzl.onlinetest.domain.ProblemSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProblemSetDao extends JpaRepository<ProblemSet,Integer>{

    /**
     * 按章节编号查询题目
     * @param cid
     * @return
     */
    @Query(
            value = "SELECT a.tk_questionid as qid,a.tk_questiontitle as qask,a.tk_questionscore as qscore,a.tk_difficultylevel as qlevel,a.tk_type as qtype,a.tk_chapterid as cid,c.tk_chaptername as cname FROM tk_questions a,tk_chapters c"+
            " where 1=1 and a.tk_chapterid = c.tk_chapterid and a.tk_questionstatus = '1' and c.tk_chapterstatus = '1'" +
            " and IF (?1 != '', a.tk_chapterid = ?1, 1 = 1)"+
            " and IF (?3 != '', a.tk_type = ?3 , 1 = 1)"+
            " and IF (?4 != '', a.tk_difficultylevel = ?4 , 1 = 1)"+
            " and IF (?2 != '', a.tk_questiontitle LIKE CONCAT('%',?2,'%'), 1 = 1)",
            countQuery = "select count(*) FROM tk_questions a,tk_chapters c"+
            " where 1=1 and a.tk_chapterid = c.tk_chapterid and a.tk_questionstatus = '1' and c.tk_chapterstatus = '1'" +
            " and IF (?1 != '', a.tk_chapterid = ?1, 1 = 1)"+
            " and IF (?3 != '', a.tk_type = ?3 , 1 = 1)"+
            " and IF (?4 != '', a.tk_difficultylevel = ?4 , 1 = 1)"+
            " and IF (?2 != '', a.tk_questiontitle LIKE CONCAT('%',?2,'%'), 1 = 1)",
            nativeQuery = true
    )
    Page<ProblemChapter> findAllByStatusAndCidAndQaskAndQtypeAndQlevelOrderByIdAsc(@Param(value = "cid") String cid, @Param(value = "qask") String qask, @Param(value = "qtype") String qtype, @Param(value = "qlevel") String qlevel, Pageable pageable);

    public interface ProblemChapter {
        public String getQid();
        public String getQask();
        public String getQscore();
        public String getQlevel();
        public String getQtype();
        public String getCid();
        public String getCname();
    }

    /**
     * 获取题目总数
     * @param status
     * @return
     */
    int countAllByStatus(String status);

    /**
     * 获取某章节的题目总数
     * @param status
     * @return
     */
    int countAllByStatusAndCid(String status,String cid);


    /**
     * 查询单个题目
     * @param qid
     * @return
     */
    ProblemSet findProblemSetByQidAndStatus(String qid,String status);


    /**
     * 保存题目
     * @param problemSet
     * @return
     */
    ProblemSet save(ProblemSet problemSet);


    /**
     * 获取最大qid
     * @return
     */
    @Query(value = "select MAX(CAST(tk_questionid AS SIGNED)) from tk_questions where tk_questionstatus = '1'",nativeQuery = true)
    String findMaxQid();

}
