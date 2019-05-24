package com.zhifa.gdou.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import com.mxixm.fastboot.weixin.annotation.WxAsyncMessage;
import com.mxixm.fastboot.weixin.annotation.WxController;
import com.mxixm.fastboot.weixin.annotation.WxMessageMapping;
import com.mxixm.fastboot.weixin.module.message.WxMessage;
import com.mxixm.fastboot.weixin.module.message.WxMessageBody;
import com.mxixm.fastboot.weixin.module.message.WxUserMessage;
import com.mxixm.fastboot.weixin.module.web.WxRequest;
import com.mxixm.fastboot.weixin.module.web.WxRequestBody;
import com.zhifa.gdou.config.baidu.TransApi;
import com.zhifa.gdou.mapper.WxImageInfoMapper;
import com.zhifa.gdou.mapper.WxMoreInfoMapper;
import com.zhifa.gdou.model.WxImageInfo;
import com.zhifa.gdou.model.WxMoreInfo;
import com.zhifa.gdou.utils.ImagesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;

@WxController
public class WxImgTextController {

    private static Logger log = LoggerFactory.getLogger(WxImgTextController.class);


    @Autowired
    private AipOcr aipOcr;

    @Autowired
    private TransApi transApi;

    @Autowired
    private WxImageInfoMapper wxImageInfoMapper;

    @Autowired
    private WxMoreInfoMapper wxMoreInfoMapper;


    /**
     * 接受用户图片消息，异步返回文本消息
     *
     * @param
     * @return the result
     */
    @WxMessageMapping(type = WxMessage.Type.IMAGE)
    public String image(WxRequest wxRequest, WxRequestBody.Image image, String content) {
        WxRequest.Body body = wxRequest.getBody();
        String picUrl = image.getPicUrl();
        String openId = wxRequest.getOpenId();
        log.info("调用了image WxMessage.Type.IMAGE => openId={}  getPicUrl={}", openId, picUrl);
        if (ObjectUtils.isEmpty(picUrl)) {
            return content;
        }
        WxImageInfo wxImageInfo = new WxImageInfo(openId, picUrl, new Date());
        wxImageInfoMapper.insert(wxImageInfo);
        // 通用文字识别, 图片参数为远程url图片
        byte[] fileStream = ImagesUtils.getFileStream(picUrl);
        com.alibaba.fastjson.JSONObject resJson = JSONObject.parseObject(aipOcr.basicAccurateGeneral(fileStream, new HashMap<String, String>()).toString(2));
        JSONArray words_results = resJson.getJSONArray("words_result");
        StringBuffer bf = new StringBuffer();
        for (int i = 0; i < words_results.size(); i++) {
            bf.append(words_results.getJSONObject(i).getString("words"));
        }
        log.info("完成读取图片文字={}", bf.toString());
        JSONObject stranslate = JSONObject.parseObject(transApi.getTransResult(bf.toString(), "auto", "en"));
        String stranslateRes = stranslate.getJSONArray("trans_result").getJSONObject(0).getString("dst");
        log.info("完成翻译= {}", stranslateRes);
        return stranslateRes;
    }


    @WxMessageMapping(type = WxMessage.Type.TEXT, wildcard = "**")
    @WxAsyncMessage
    public WxUserMessage text(WxRequest wxRequest, String content) {
        log.info("调用了 text WxMessage.Type.TEXT wxRequest==>{}", wxRequest);
        WxUserMessage build = null;
        if (content.matches("[\\d]+")) {
            WxMoreInfo wxMoreInfo = wxMoreInfoMapper.selectByPrimaryKey(Integer.valueOf(content));
            if (!ObjectUtils.isEmpty(wxMoreInfo)) {
                build = WxMessage.newsBuilder()
                        .addItem(WxMessageBody.News.Item.builder().title(wxMoreInfo.getTitle()).description(wxMoreInfo.getDescription())
                                .picUrl(wxMoreInfo.getPicurl())
                                .url(wxMoreInfo.getUrl()).build())
                        .build();
                return build;
            }
        }
        build = WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title("成绩").description("点击查看")
                        .picUrl("http://mmbiz.qpic.cn/mmbiz_jpg/BXa2ick0Zc8mhZBGSicbe5xd8q1vbESAWOjjOKw4icggiaZlIUP0Woj8FibWHp8yeVLvCJbwg46BfQLCPUlf8WeCib4g/0")
                        .url("/wx/main").build())
                .build();

        return build;

    }




























/*
switch (content) {
            case "1":
                build = build1();
                break;
            case "2":
                build = build2();
                break;
            case "3":
                build = build3();
                break;
            case "4":
                build = build4();
                break;
            case "5":
                build = build5();
                break;
            case "6":
                build = build6();
                break;
            case "7":
                build = build7();
                break;
            case "8":
                build = build8();
                break;
            case "9":
                build = build9();
                break;
            case "10":
                build = build10();
                break;
            case "11":
                build = build11();
                break;
            default:
                build = moren();
        }
    private WxUserMessage moren() {
        return WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title("成绩").description("点击查看")
                        .picUrl("http://mmbiz.qpic.cn/mmbiz_jpg/BXa2ick0Zc8mhZBGSicbe5xd8q1vbESAWOjjOKw4icggiaZlIUP0Woj8FibWHp8yeVLvCJbwg46BfQLCPUlf8WeCib4g/0")
                        .url("/wx/main").build())
                .build();
    }

    private WxUserMessage build1() {
        WxUserMessage build = WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title("小学学科网").description("点击查看")
                        .picUrl("http://www.xuekeedu.com/img/v3/logo_xxxk.png")
                        .url("http://www.xuekeedu.com/").build())
                .build();
        return build;
    }

    private WxUserMessage build2() {
        WxUserMessage build = WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title("小学教课资源").description("点击查看")
                        .picUrl("http://ziyuan.shuziyuwen.com/upload/201904/15/201904151739408576.png")
                        .url("http://ziyuan.shuziyuwen.com/qbzy/index.html").build())
                .build();
        return build;
    }

    private WxUserMessage build3() {

        WxUserMessage build = WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title("原中小学教育资源网").description("点击查看")
                        .picUrl("http://static.ruiwen.com/img/logo.png")
                        .url("http://www.ruiwen.com/").build())
                .build();
        return build;
    }

    private WxUserMessage build4() {
        WxUserMessage build = WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title("初中教学资源网").description("点击查看")
                        .picUrl("http://cz.jb1000.com/Images/logo.gif")
                        .url("http://cz.jb1000.com/").build())
                .build();
        return build;
    }

    private WxUserMessage build5() {
        WxUserMessage build = WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title("中学课件网").description("点击查看")
                        .picUrl("http://zxxkstatic.zxxk.com/ChildSite/skins/kejian/img/logo.jpg")
                        .url("http://kj.zxxk.com/").build())
                .build();
        return build;
    }

    private WxUserMessage build6() {
        WxUserMessage build = WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title("高考资源网").description("点击查看")
                        .picUrl("https://www.ks5u.com/zt/2019gkrdtx/images/zlfx-logo.png")
                        .url("https://www.ks5u.com/index.shtml").build())
                .build();
        return build;
    }

    private WxUserMessage build7() {
        WxUserMessage build = WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title("全国高考分数线").description("点击查看")
                        .picUrl("http://tb1.bdstatic.com/tb/cms/ngmis/file_1528341781654.jpg")
                        .url("http://kaoshi.edu.sina.com.cn/college/scorelist").build())
                .build();
        return build;
    }

    private WxUserMessage build8() {
        WxUserMessage build = WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title("全国高考分数查询入口").description("点击查看")
                        .picUrl("https://www.eol.cn/favicon.ico")
                        .url("https://www.eol.cn/e_html/gk/chafen/index.shtml?province=bj#bj").build())
                .build();
        return build;
    }

    private WxUserMessage build9() {
        WxUserMessage build = WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title("模拟志愿填报").description("点击查看")
                        .picUrl("https://node-img.b0.upaiyun.com/wmzy-pc/5.9.1/images/logo-wmzy/logo-v6.png")
                        .url("https://www.wmzy.com/").build())
                .build();
        return build;
    }

    private WxUserMessage build10() {
        WxUserMessage build = WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title("志愿填报入口").description("点击查看")
                        .picUrl("https://gkcx.eol.cn/static/assets/images/top-logo-hash.png")
                        .url("https://gkcx.eol.cn/").build())
                .build();
        return build;
    }

    private WxUserMessage build11() {
        WxUserMessage build = WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title("高考专业").description("点击查看")
                        .picUrl("http://cdn.stc.gaokaopai.com/Public/Images/career/09.jpg")
                        .url("http://www.gaokaopai.com/zhiye.html").build())
                .build();
        return build;
    }
*/


}
