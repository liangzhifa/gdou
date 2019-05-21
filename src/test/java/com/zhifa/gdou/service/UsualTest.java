package com.zhifa.gdou.service;

import org.apache.commons.codec.binary.StringUtils;

public class UsualTest {
    public static void main(String[] args) {
        byte[] s1 = org.apache.tomcat.util.codec.binary.StringUtils.getBytesUtf8("略阳县支行");
        String s2 = org.apache.tomcat.util.codec.binary.StringUtils.newStringUtf8(s1);
        System.out.println(s2);
        byte[] bytes = StringUtils.getBytesUtf8("略阳县支行");
        String s = null;

        s = new String(bytes);

        System.out.println(s);

    }
}
