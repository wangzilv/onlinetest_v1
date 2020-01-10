package com.wzl.onlinetest.domain;

public class ActionResult {

    private boolean success;

    private String message;

    private Object data;

    private String code;


    public ActionResult() {
    }

    public ActionResult(boolean success) {
        this(success, null, null);
    }

    public ActionResult(boolean success, String message) {
        this(success, message, null);
    }

    public ActionResult(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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