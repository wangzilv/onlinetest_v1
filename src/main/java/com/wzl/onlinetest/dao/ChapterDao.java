package com.wzl.onlinetest.dao;

import com.wzl.onlinetest.domain.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChapterDao extends JpaRepository<Chapter,Integer> {

    /**
     * 分页查询所有的章节
     * @return
     */
    Page<Chapter> findAllByStatus(Pageable pageable,String status);

    /**
     * 查询所有章节
     * @param status
     * @return
     */
    List<Chapter> findAllByStatus(String status);

    /**
     * 模糊查询章节信息
     * @param pageable
     * @param cname
     * @param cid
     * @param status
     * @return
     */
    List<Chapter> findChapterByCnameOrCidAndStatus(Pageable pageable, String cname, String cid, String status);
    /**
     * 新增、修改、删除章节信息
     * @param chapter
     * @return
     */
    Chapter save(Chapter chapter);


    /**
     * 按章节编号查询章节信息
     * @param cid
     * @param status
     * @return
     */
    Chapter findChapterByCidAndStatus(String cid,String status);

    /**
     * 获取最大cid
     * @return
     */
    @Query(value = "select MAX(CAST(tk_chapterid AS SIGNED)) from tk_chapters where tk_chapterstatus = '1'",nativeQuery = true)

    String findMaxCid();
}
