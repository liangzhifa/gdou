package com.zhifa.gdou.config;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.ocr.AipOcr;
import com.zhifa.gdou.config.baidu.TransApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaiduConfig {
    private static Logger log = LoggerFactory.getLogger(BaiduConfig.class);

    //设置APPID/AK/SK
    @Value("${baidu.ZhitiShiBie.APP_ID}")
    private String ZhitiShiBie_APP_ID;
    @Value("${baidu.ZhitiShiBie.API_KEY}")
    private String ZhitiShiBie_API_KEY;
    @Value("${baidu.ZhitiShiBie.SECRET_KEY}")
    private String ZhitiShiBie_SECRET_KEY;
    @Value("${baidu.trans.APP_ID}")
    private String transAPP_ID;
    @Value("${baidu.trans.SECURITY_KEY}")
    private String transSECRET_KEY;

   /* @Bean
    public AipImageClassify client(){
        log.info("初始化 百度配置 APP_ID={},API_KEY={},SECRET_KEY={}",ZhitiShiBie_APP_ID,ZhitiShiBie_API_KEY,ZhitiShiBie_SECRET_KEY);
        return new AipImageClassify(ZhitiShiBie_APP_ID, ZhitiShiBie_API_KEY, ZhitiShiBie_SECRET_KEY);
    }*/

   @Bean
   public  AipOcr aipOcr(){
       // 初始化一个AipOcr
       return new AipOcr(ZhitiShiBie_APP_ID, ZhitiShiBie_API_KEY, ZhitiShiBie_SECRET_KEY);
   }

    @Bean
    public  TransApi transApi(){
        log.info("初始化 百度翻译 配置 transAPP_ID={},transSECRET_KEY={}",transAPP_ID,transSECRET_KEY);
        return new TransApi(transAPP_ID, transSECRET_KEY);
    }


}
