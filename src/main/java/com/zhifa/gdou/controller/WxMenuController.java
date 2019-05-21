package com.zhifa.gdou.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import com.mxixm.fastboot.weixin.annotation.WxAsyncMessage;
import com.mxixm.fastboot.weixin.annotation.WxButton;
import com.mxixm.fastboot.weixin.annotation.WxEventMapping;
import com.mxixm.fastboot.weixin.annotation.WxMessageMapping;
import com.mxixm.fastboot.weixin.module.event.WxEvent;
import com.mxixm.fastboot.weixin.module.message.WxMessage;
import com.mxixm.fastboot.weixin.module.message.WxMessageBody;
import com.mxixm.fastboot.weixin.module.user.WxUser;
import com.mxixm.fastboot.weixin.module.web.WxRequest;
import com.mxixm.fastboot.weixin.module.web.WxRequestBody;
import com.mxixm.fastboot.weixin.module.web.session.WxSession;
import com.zhifa.gdou.config.baidu.TransApi;
import com.zhifa.gdou.mapper.StudentInfoMapper;
import com.zhifa.gdou.mapper.TeacherMapper;
import com.zhifa.gdou.mapper.WxImageInfoMapper;
import com.zhifa.gdou.mapper.WxUserAttentionMapper;
import com.zhifa.gdou.model.WxImageInfo;
import com.zhifa.gdou.model.WxUserAttention;
import com.zhifa.gdou.utils.ImagesUtils;
import com.zhifa.gdou.utils.WxBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;


@com.mxixm.fastboot.weixin.annotation.WxController
public class WxMenuController {



    private static Logger log = LoggerFactory.getLogger(WxMenuController.class);

    @Value("${wx.callback-url}")
    private String serverUrl;

    @Autowired
    private WxUserAttentionMapper wxUserAttentionMapper;

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 定义微信菜单
     */
    @WxButton(group = WxButton.Group.LEFT, main = true, name = "学生信息")
    public void left() {
    }

    /**
     * 定义微信菜单
     */
    @WxButton(group = WxButton.Group.MIDDLE, main = true, name = "学习工具")
    public void center() {
    }
    /**
     * 定义微信菜单
     */
    @WxButton(group = WxButton.Group.RIGHT, main = true, name = "考试咨询")
    public void right() {
    }



    @WxButton(type =WxButton.Type.VIEW,
            group = WxButton.Group.RIGHT,
            order = WxButton.Order.FIRST,
            url = "http://www.gdou.edu.cn/",
            name="教务处")
    @WxAsyncMessage
    public void guanwang(WxRequest wxRequest){
    }

    @WxButton(type =WxButton.Type.VIEW,
            group = WxButton.Group.RIGHT,
            order = WxButton.Order.SECOND,
            url = "http://m.gaosan.com/guangdonggaokao/",
            name="高考")
    @WxAsyncMessage
    public void gaokao(WxRequest wxRequest){
    }

    @WxButton(type =WxButton.Type.VIEW,
            group = WxButton.Group.RIGHT,
            order = WxButton.Order.THIRD,
            url = "http://gz.zhongkao.com/",
            name="中考")
    @WxAsyncMessage
    public void zhongkao(WxRequest wxRequest){
    }

      @WxButton(type =WxButton.Type.VIEW,
            group = WxButton.Group.RIGHT,
            order = WxButton.Order.FORTH,
            url = "https://www.chsi.com.cn/",
            name="学信网")
    @WxAsyncMessage
    public void xuexinwang(WxRequest wxRequest){
    }



    /**学生相关
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.VIEW,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.FIRST,
            url = "/wx/main",
            name = "成绩相关")
    @WxAsyncMessage
    public void link(WxRequest wxRequest) {
    }

    /**学生相关
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.VIEW,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.SECOND,
            url = "/wx/txl",
            name = "通讯录")
    @WxAsyncMessage
    public void class_info(WxRequest wxRequest) {
    }
 /**学生相关
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.VIEW,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.THIRD,
            url = "/wx/liuyan",
            name = "留言信息")
    @WxAsyncMessage
    public void liuyan(WxRequest wxRequest) {
    }



    /**清除登录
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.CLICK,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.FORTH,
            name = "清除登录")
    public String leftSECOND(WxRequest wxRequest, WxUser wxUser) {
        //log.info("wxRequest=>{}",wxRequest);
        log.info("清除登录，wxUser=>{}",wxUser);
       /* WxUserAttention wxUserAttention = WxBeanUtil.WxUserToWxUserAttention(wxUser);
        int insert = wxUserAttentionMapper.insert(wxUserAttention);*/

        String openId = wxUser.getOpenId();

        int i = teacherMapper.updateOpenIdByOpenId(openId);

        int k = studentInfoMapper.updateOpenIdByOpenId(openId);
        if (i>0||k>0){
            return "账号信息清除成功";
        }

        return "账号信息清除失败(或者没绑定)";
    }




    /**图像识别
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.PIC_PHOTO_OR_ALBUM,
            group = WxButton.Group.MIDDLE,
            order = WxButton.Order.FIRST,
            name = "拍照翻译")
    @WxAsyncMessage
    public void PIC_PHOTO_OR_ALBUM(WxRequest wxRequest) {
      //  WxRequest.Body body = wxRequest.getBody();

    }



    @WxButton(type =WxButton.Type.VIEW,
            group = WxButton.Group.MIDDLE,
            order = WxButton.Order.SECOND,
            url = "https://fanyi.baidu.com/?aldtype=16047#auto/zh",
            name="翻译工具")
    @WxAsyncMessage
    public void youdaofanyi(WxRequest wxRequest){
    }

    @WxButton(type =WxButton.Type.VIEW,
            group = WxButton.Group.MIDDLE,
            order = WxButton.Order.THIRD,
            url = "https://m.gushiwen.org/",
            name="古诗文")
    @WxAsyncMessage
    public void gushiwen(WxRequest wxRequest){
    }

    /**
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.CLICK,
            group = WxButton.Group.RIGHT,
            order = WxButton.Order.FIFTH,
            name = "更多信息")
    public String gengduowangzhan(WxRequest wxRequest, WxUser wxUser) {
        StringBuilder sb = new StringBuilder();
        sb.append("回复对应数字跳转到对应网站哦O(∩_∩)O~").append("\n");
        sb.append("1  小学学科网").append("\n");
        sb.append("2  小学教课资源").append("\n");
        sb.append("3  原中小学教育资源网").append("\n");
        sb.append("4  初中教学资源网").append("\n");
        sb.append("5  中学课件网").append("\n");
        sb.append("6  高考资源网").append("\n");
        sb.append("7  全国高考分数线").append("\n");
        sb.append("8  高考分数查询入口").append("\n");
        sb.append("9  模拟志愿填报").append("\n");
        sb.append("10  志愿填报入口").append("\n");
        sb.append("11  高考专业").append("\n");


        String str =sb.toString();
        return str;
    }




    /**退订
     * 接受微信事件
     * @param wxRequest
     * @param wxUser
     */
    @WxEventMapping(type = WxEvent.Type.UNSUBSCRIBE)
    public void unsubscribe(WxRequest wxRequest, WxUser wxUser) {
        System.out.println(wxUser.getNickName() + "退订了公众号");
    }

    /**
     * 订阅
     * @param wxRequest
     * @param wxUser
     * @return
     */
    @WxEventMapping(type = WxEvent.Type.SUBSCRIBE)
    public String subscribe(WxRequest wxRequest, WxUser wxUser) {
        System.out.println(wxUser.getNickName() + "订了公众号");
        log.info("wxRequest==>{},\nwxUser==>{}",wxRequest,wxUser);
        WxUserAttention wxUserAttention = wxUserAttentionMapper.selectByOpenId(wxUser.getOpenId());
        if (ObjectUtils.isEmpty(wxUserAttention)){
            wxUserAttentionMapper.insert(WxBeanUtil.WxUserToWxUserAttention(wxUser));
        }
        return "非常感谢 "+wxUser.getNickName() + " 订阅GDOU家校服务   /:rose";
    }





}
