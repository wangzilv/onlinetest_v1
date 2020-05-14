package com.wzl.onlinetest.domain;

import com.alibaba.excel.annotation.ExcelProperty;

public class TestPaperExcelModel {
    @ExcelProperty(index = 0)
    private String column1;

    @ExcelProperty(index = 1)
    private String column2;

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }
}
