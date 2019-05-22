package com.zhifa.gdou.service;

import org.apache.commons.codec.binary.StringUtils;

public class UsualTest {
    public static void main(String[] args) {
        String b[] = {"132","132a","a132","1a2","*54","545*","5465$%54","你好"," "};

        for (int i = 0; i <b.length; i++) {
            boolean matches = b[i].matches("[\\d]+");
            System.out.println((i+1)+"=====>"+matches);
        }


        byte[] s1 = org.apache.tomcat.util.codec.binary.StringUtils.getBytesUtf8("略阳县支行");
        String s2 = org.apache.tomcat.util.codec.binary.StringUtils.newStringUtf8(s1);
        System.out.println(s2);
        byte[] bytes = StringUtils.getBytesUtf8("略阳县支行");
        String s = null;

        s = new String(bytes);

        System.out.println(s);

    }
}
