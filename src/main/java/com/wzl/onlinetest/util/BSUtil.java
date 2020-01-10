package com.wzl.onlinetest.util;

import com.wzl.onlinetest.config.BusinessException;

public class BSUtil {
    public static void throwBusinessException(boolean expression, String error){
        if(!expression) {
            throw new BusinessException(error);
        }
    }
}
