package com.wzl.onlinetest.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class ProblemExcelModel extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String column1;

    @ExcelProperty(index = 1)
    private String column2;

    @ExcelProperty(index = 2)
    private String column3;

    @ExcelProperty(index = 3)
    private String column4;

    @ExcelProperty(index = 4)
    private String column5;

    @ExcelProperty(index = 5)
    private String column6;

    @ExcelProperty(index = 6)
    private String column7;


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

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    public String getColumn5() {
        return column5;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }

    public String getColumn6() {
        return column6;
    }

    public void setColumn6(String column6) {
        this.column6 = column6;
    }

    public String getColumn7() {
        return column7;
    }

    public void setColumn7(String column7) {
        this.column7 = column7;
    }

    @Override
    public String toString() {
        return "ExcelModel{" +
                "column1='" + column1 + '\'' +
                ", column2='" + column2 + '\'' +
                ", column3='" + column3 + '\'' +
                ", column4='" + column4 + '\'' +
                ", column5='" + column5 + '\'' +
                ", column6='" + column6 + '\'' +
                ", column7='" + column7 + '\'' +
                '}';
    }
}
