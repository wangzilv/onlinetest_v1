package com.wzl.onlinetest.config;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 7727483885765251420L;
    public BusinessException(String message){
        super(message);
    }
}
