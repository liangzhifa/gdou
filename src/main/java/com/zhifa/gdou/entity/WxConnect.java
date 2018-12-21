package com.zhifa.gdou.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxConnect implements Serializable {

    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;

}
