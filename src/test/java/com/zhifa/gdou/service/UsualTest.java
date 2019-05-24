package com.zhifa.gdou.service;

public class UsualTest {
    public static void main(String[] args) {
        String str = "<p style=\"text-align:  center;\">< img        src=\"http://zhifa.daiqee.com/ab05b153-77bf-4867-b7fb-38b121c3f41dtimg.jpg\" style=\"max-width:30%;\"></p ><p style=\"text-align: center;\">< img src=\"http://zhifa.daiqee.com/be70f945-447b-4364-b9fd-2b1b6c5d1680test22.jpg\" style=\"max-width:30%;\"></p ><p style=\"text-align: center;\">啊实打实大苏打撒旦发的方式告诉梵蒂冈士大夫敢死队风格岁的法国<br></p >";
        /*String temp = "qwwwwwwwww";
        str = temp;*/
        str=str.replaceAll("[ ]+", "");
        //System.out.println(str);
        int start = str.indexOf("<imgsrc=\"")+9;//没找到 返回 -1  加上9 就是8
        int end=0;
        if (!(start==8)){//证明下面有图片
            for (int k = start; k <str.length()-1; k++) {
                String s = str.substring(k, k + 1);
                if (s.equals("\"")){
                    end=k;
                    break;
                }
            }
            System.out.println(str.substring((start),end));
            //return 找到
        }
        //return 找不到


        String key = "x x sd x  .jpg";
        key = key.substring(key.lastIndexOf("."));
        System.out.println(key);


    }
}
