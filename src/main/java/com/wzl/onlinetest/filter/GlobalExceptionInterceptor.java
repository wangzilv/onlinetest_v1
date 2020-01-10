package com.wzl.onlinetest.filter;

import com.wzl.onlinetest.config.BusinessException;
import com.wzl.onlinetest.config.MyResponseBodyAdvice;
import com.wzl.onlinetest.constants.StaticDataConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyResponseBodyAdvice.class);

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> handleException(Exception e){
        String failMsg = e.getMessage();
        logger.error(failMsg, e);
        Map<String,Object> map = new HashMap<>();
        if (e instanceof MethodArgumentNotValidException) {/* 拿到参数校验具体异常信息提示*/
            failMsg = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
        }
        map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.FAIL);
        map.put(StaticDataConstants.resultMsg.MSG,failMsg);
        return map;
    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Map<String,Object> handleBusinessException(BusinessException e){
        String failMsg = e.getMessage();
        logger.error(failMsg, e);
        Map<String,Object> map = new HashMap<>();
        map.put(StaticDataConstants.resultMsg.CODE,StaticDataConstants.resultMsg.FAIL);
        map.put(StaticDataConstants.resultMsg.MSG,failMsg);
        return map;
    }
}