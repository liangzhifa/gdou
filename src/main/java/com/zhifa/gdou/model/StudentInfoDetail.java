package com.zhifa.gdou.model;

public class StudentInfoDetail {
    private String stuno;

    private String phone;

    private String email;

    private String address;

    public String getStuno() {
        return stuno;
    }

    public void setStuno(String stuno) {
        this.stuno = stuno == null ? null : stuno.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public StudentInfoDetail() {
    }

    public StudentInfoDetail(String stuno) {
        this.stuno = stuno;
    }

    public StudentInfoDetail(String stuno, String phone, String email, String address) {
        this.stuno = stuno;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}