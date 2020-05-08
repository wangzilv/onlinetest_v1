package com.wzl.onlinetest.constants;

public class StaticDataConstants {
    private StaticDataConstants() {

    }
    //Ajax返回的json串
    public final static class resultMsg{
        private resultMsg(){}
        public final static String CODE = "code";
        public final static String MSG = "msg";
        public final static String FAIL = "0";
        public final static String SUCCESS = "1";
    }

    //题目类型
    public  final static class problemType{
        public final static String SINGLE_CHOICE = "1";
        public final static String MULTIPLE_CHOICE = "2";
        public final static String TRUE_OR_FALSE = "3";
        public final static String CORRECTION_PROBLEM = "4";
        public final static String CODE_PROBLEM = "5";
    }

    //状态
    public  final static class status{
        public final static String U = "1";
        public final static String E = "0";

    }
}
