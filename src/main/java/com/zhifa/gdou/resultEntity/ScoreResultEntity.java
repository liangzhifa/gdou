package com.zhifa.gdou.resultEntity;

import java.io.Serializable;
import java.util.Date;

public class ScoreResultEntity implements Serializable {



    private String studentname;
    private String studentnum;
    private Date testTime;
    private Integer yuwen;
    private Integer shuxue;
    private Integer yingyu;
    private Integer wuli;
    private Integer huaxue;
    private Integer shengwu;
    private Integer zongfen;

    public Integer getZongfen() {
        return zongfen;
    }

    public void setZongfen(Integer zongfen) {
        this.zongfen = zongfen;
    }

    public ScoreResultEntity(String studentname, String studentnum, Date testTime, Integer yuwen, Integer shuxue, Integer yingyu, Integer wuli, Integer huaxue, Integer shengwu, Integer zongfen) {
        this.studentname = studentname;
        this.studentnum = studentnum;
        this.testTime = testTime;
        this.yuwen = yuwen;
        this.shuxue = shuxue;
        this.yingyu = yingyu;
        this.wuli = wuli;
        this.huaxue = huaxue;
        this.shengwu = shengwu;
        this.zongfen = zongfen;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(String studentnum) {
        this.studentnum = studentnum;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public Integer getYuwen() {
        return yuwen;
    }

    public void setYuwen(Integer yuwen) {
        this.yuwen = yuwen;
    }

    public Integer getShuxue() {
        return shuxue;
    }

    public void setShuxue(Integer shuxue) {
        this.shuxue = shuxue;
    }

    public Integer getYingyu() {
        return yingyu;
    }

    public void setYingyu(Integer yingyu) {
        this.yingyu = yingyu;
    }

    public Integer getWuli() {
        return wuli;
    }

    public void setWuli(Integer wuli) {
        this.wuli = wuli;
    }

    public Integer getHuaxue() {
        return huaxue;
    }

    public void setHuaxue(Integer huaxue) {
        this.huaxue = huaxue;
    }

    public Integer getShengwu() {
        return shengwu;
    }

    public void setShengwu(Integer shengwu) {
        this.shengwu = shengwu;
    }

    public ScoreResultEntity() {
    }

}
