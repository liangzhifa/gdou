package com.zhifa.gdou.model;

public class StudentInfo {
    private Integer id;

    private String studentname;

    private String studentnum;

    private String studentpass;

    private String openid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname == null ? null : studentname.trim();
    }

    public String getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(String studentnum) {
        this.studentnum = studentnum == null ? null : studentnum.trim();
    }

    public String getStudentpass() {
        return studentpass;
    }

    public void setStudentpass(String studentpass) {
        this.studentpass = studentpass == null ? null : studentpass.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id=" + id +
                ", studentname='" + studentname + '\'' +
                ", studentnum='" + studentnum + '\'' +
                ", studentpass='" + studentpass + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}