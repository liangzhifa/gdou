package com.zhifa.gdou.model;

import java.io.Serializable;

public class ClassInfo implements Serializable {
    private Integer id;

    private Integer classNum;

    private String className;

    private String headMasterNum;

    private String studentnum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getHeadMasterNum() {
        return headMasterNum;
    }

    public void setHeadMasterNum(String headMasterNum) {
        this.headMasterNum = headMasterNum == null ? null : headMasterNum.trim();
    }

    public String getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(String studentnum) {
        this.studentnum = studentnum == null ? null : studentnum.trim();
    }
}