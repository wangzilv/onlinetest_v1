package com.wzl.onlinetest.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;
import com.wzl.onlinetest.constants.StaticDataConstants;
import com.wzl.onlinetest.domain.ProblemExcelModel;
import com.wzl.onlinetest.domain.ProblemSet;
import com.wzl.onlinetest.domain.TestPaper;
import com.wzl.onlinetest.domain.TestPaperExcelModel;
import com.wzl.onlinetest.service.TestPaperService;
import com.wzl.onlinetest.service.TestPaperServiceImpl;
import com.wzl.onlinetest.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板的读取类
 *
 * @author Jiaju Zhuang
 */
// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class UploadTestPaperListener extends AnalysisEventListener<TestPaperExcelModel> {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(UploadTestPaperListener.class);

    private static final int BATCH_COUNT = 5;
    List<TestPaperExcelModel> list = new ArrayList<TestPaperExcelModel>();
    private static int count = 1;

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private TestPaperService testPaperService;

    public UploadTestPaperListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        testPaperService = new TestPaperServiceImpl();
    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param testPaperService
     */
    public UploadTestPaperListener(TestPaperService testPaperService) {
        this.testPaperService = testPaperService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(TestPaperExcelModel data, AnalysisContext context) {
        System.out.println("解析到一条数据:{ "+ data.toString() +" }");
        list.add(data);
        count ++;
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    @Transactional(rollbackFor=Exception.class)
    void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        long time1=System.currentTimeMillis();
        for (TestPaperExcelModel excelModel:list) {
            String testid = testPaperService.findMaxTestid();
            if(StringUtils.isEmpty(testid)){
                testid = "0";
            }
            Long newQid = Long.valueOf(testid)+1;
            TestPaper testPaper = new TestPaper();
            testPaper.setTestid(newQid.toString());
            testPaper.setTesttitle(excelModel.getColumn1());
            testPaper.setTestdesc(excelModel.getColumn2());
            testPaper.setTestremark("导入测试"+newQid);
            testPaper.setTesttype(StaticDataConstants.testPaperType.AutoTestPaper);
            testPaper.setTeststatus(StaticDataConstants.status.U);
            testPaper.setCreateTime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
            testPaperService.save(testPaper);
        }
        long time2=System.currentTimeMillis();
        LOGGER.info("存储数据库成功！");
        LOGGER.info("耗时："+(time2-time1)+"ms");
    }
}