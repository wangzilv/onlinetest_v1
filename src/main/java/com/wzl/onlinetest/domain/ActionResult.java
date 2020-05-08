package com.wzl.onlinetest.domain;

public class ActionResult {

    private String result;

    private Object data;

    private String message;

    private String code;


    public ActionResult() {
    }

    public ActionResult(String result) {
        this(result, null, null);
    }

    public ActionResult(String result, Object data) {
        this(result, data, null);
    }

    public ActionResult(String result, Object data,String message ) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}