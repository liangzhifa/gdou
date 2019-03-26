package com.zhifa.gdou.utils;

import java.io.Serializable;
import java.util.Date;

public class ScoreTempEntity implements Serializable {
    private String studentnum;
    private Date testTime;

    public ScoreTempEntity() {
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

    public ScoreTempEntity(String studentnum, Date testTime) {
        this.studentnum = studentnum;
        this.testTime = testTime;
    }
}
