package com.zhifa.gdou.resultEntity;

import java.io.Serializable;

public class ImgDto implements Serializable {
    private String imgUrl;
    private String content;
    private String openId;

    @Override
    public String toString() {
        return "ImgDto{" +
                "imgUrl='" + imgUrl + '\'' +
                ", content='" + content + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOpenId() {
        return openId;
    }

    public ImgDto(String imgUrl, String content, String openId) {
        this.imgUrl = imgUrl;
        this.content = content;
        this.openId = openId;
    }

    public ImgDto() {
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
