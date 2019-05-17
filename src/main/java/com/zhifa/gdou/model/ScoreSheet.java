package com.zhifa.gdou.model;

import java.io.Serializable;
import java.util.Date;

public class ScoreSheet implements Serializable {
    private Integer id;

    private String studentnum;

    private String course;

    private Integer score;

    private Date testTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(String studentnum) {
        this.studentnum = studentnum == null ? null : studentnum.trim();
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course == null ? null : course.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public ScoreSheet() {
    }

    public ScoreSheet(String studentnum, String course, Integer score, Date testTime) {
        this.studentnum = studentnum;
        this.course = course;
        this.score = score;
        this.testTime = testTime;
    }

    @Override
    public String toString() {
        return "ScoreSheet{" +
                "id=" + id +
                ", studentnum='" + studentnum + '\'' +
                ", course='" + course + '\'' +
                ", score=" + score +
                ", testTime=" + testTime +
                '}';
    }
}