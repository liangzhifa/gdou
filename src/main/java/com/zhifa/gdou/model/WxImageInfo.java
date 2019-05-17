package com.zhifa.gdou.model;

import java.io.Serializable;
import java.util.Date;

public class WxImageInfo implements Serializable {
    private Integer id;

    private String openid;

    private String picurl;

    private Date creattime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public WxImageInfo(String openid, String picurl, Date creattime) {
        this.openid = openid;
        this.picurl = picurl;
        this.creattime = creattime;
    }

    public WxImageInfo() {
    }
}