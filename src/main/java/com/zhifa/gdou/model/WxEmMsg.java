package com.zhifa.gdou.model;

import java.io.Serializable;
import java.util.Date;

public class WxEmMsg implements Serializable {
    private String uuid;

    private String openid;

    private Date testTime;

    private String wxMsgStatus;

    private String emailMsgStatus;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public String getWxMsgStatus() {
        return wxMsgStatus;
    }

    public void setWxMsgStatus(String wxMsgStatus) {
        this.wxMsgStatus = wxMsgStatus == null ? null : wxMsgStatus.trim();
    }

    public String getEmailMsgStatus() {
        return emailMsgStatus;
    }

    public void setEmailMsgStatus(String emailMsgStatus) {
        this.emailMsgStatus = emailMsgStatus == null ? null : emailMsgStatus.trim();
    }

    public WxEmMsg() {
    }

    public WxEmMsg(String uuid, String openid, Date testTime) {
        this.uuid = uuid;
        this.openid = openid;
        this.testTime = testTime;
        this.wxMsgStatus = "0";
        this.emailMsgStatus = "0";
    }
}