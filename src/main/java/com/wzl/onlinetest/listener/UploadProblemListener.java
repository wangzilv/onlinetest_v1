package com.wzl.onlinetest.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;
import com.wzl.onlinetest.constants.StaticDataConstants;
import com.wzl.onlinetest.domain.ProblemExcelModel;
import com.wzl.onlinetest.domain.ProblemSet;
import com.wzl.onlinetest.service.ProblemSetService;
import com.wzl.onlinetest.service.ProblemSetServiceImpl;
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
public class UploadProblemListener extends AnalysisEventListener<ProblemExcelModel> {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(UploadProblemListener.class);

    private static final int BATCH_COUNT = 5;
    List<ProblemExcelModel> list = new ArrayList<ProblemExcelModel>();
    private static int count = 1;

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private ProblemSetService problemSetService;

    public UploadProblemListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        problemSetService = new ProblemSetServiceImpl();
    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param problemSetService
     */
    public UploadProblemListener(ProblemSetService problemSetService) {
        this.problemSetService = problemSetService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ProblemExcelModel data, AnalysisContext context) {
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
        for (ProblemExcelModel excelModel:list) {
            String qid = problemSetService.findMaxQid();
            if(StringUtils.isEmpty(qid)){
                qid = "0";
            }
            Long newQid = Long.valueOf(qid)+1;
            ProblemSet problemSet = new ProblemSet();
            problemSet.setQid(newQid.toString());
            problemSet.setQask(excelModel.getColumn1());
            problemSet.setQcon(excelModel.getColumn2());
            problemSet.setQanswer(excelModel.getColumn3());
            problemSet.setCid(excelModel.getColumn4());
            problemSet.setQtype(excelModel.getColumn5());
            problemSet.setQlevel(excelModel.getColumn6());
            problemSet.setQscore(excelModel.getColumn7());
            problemSet.setStatus(StaticDataConstants.status.U);
            problemSet.setCreateTime(TimeUtil.getTime("yyyy-MM-dd HH:mm:ss"));
            problemSetService.save(problemSet);
        }
        long time2=System.currentTimeMillis();
        LOGGER.info("存储数据库成功！");
        LOGGER.info("耗时："+(time2-time1)+"ms");
    }
}