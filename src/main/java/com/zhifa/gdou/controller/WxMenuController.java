package com.zhifa.gdou.controller;

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
import com.zhifa.gdou.mapper.StudentInfoMapper;
import com.zhifa.gdou.mapper.WxUserAttentionMapper;
import com.zhifa.gdou.model.WxUserAttention;
import com.zhifa.gdou.utils.WxBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;


@com.mxixm.fastboot.weixin.annotation.WxController
public class WxMenuController {

    private static Logger log = LoggerFactory.getLogger(WxMenuController.class);

    @Autowired
    private WxUserAttentionMapper wxUserAttentionMapper;

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    /**
     * 定义微信菜单
     */
    @WxButton(group = WxButton.Group.LEFT, main = true, name = "学生信息")
    public void left() {
    }

    /**
     * 定义微信菜单
     */
    @WxButton(group = WxButton.Group.RIGHT, main = true, name = "学习工具")
    public void right() {
    }

    /**
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.CLICK,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.FIRST,
            name = "消息tips")
    public String leftFirst(WxRequest wxRequest, WxUser wxUser) {
        //log.info("wxRequest=>{}",wxRequest);
        log.info("wxUser=>{}",wxUser);
      /*  WxUserAttention wxUserAttention = WxBeanUtil.WxUserToWxUserAttention(wxUser);
        int insert = wxUserAttentionMapper.insert(wxUserAttention);*/
        return wxUser.getNickName()+"  你好啊！/:rose \n"
                                +"家长留言格式(#内容)\n"
                                +"教师群发家庭作业($内容)";
    }
    /**
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.CLICK,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.SECOND,
            name = "留言回复")
    public String leftSECOND(WxRequest wxRequest, WxUser wxUser) {
        //log.info("wxRequest=>{}",wxRequest);
        log.info("wxUser=>{}",wxUser);
      /*  WxUserAttention wxUserAttention = WxBeanUtil.WxUserToWxUserAttention(wxUser);
        int insert = wxUserAttentionMapper.insert(wxUserAttention);*/
        return wxUser.getNickName()+"  留言回复：";
    }


    /**
     * 定义微信菜单，并接受事件
     */
    @WxButton(type = WxButton.Type.VIEW,
            group = WxButton.Group.LEFT,
            order = WxButton.Order.THIRD,
            url = "/wx/main",
            name = "学生相关")
    @WxAsyncMessage
    public void link(WxRequest wxRequest) {

/*
        wxRequest.
        session.setAttribute("wxUser",wxUser);
*/
        //System.out.println();
        //return WxMessage.newsBuilder().addItem("测试图文消息", "测试", "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white.png", "http://baidu.com").build();
    }
    @WxButton(type =WxButton.Type.VIEW,
            group = WxButton.Group.RIGHT,
            order = WxButton.Order.FIFTH,
            url = "https://fanyi.baidu.com/?aldtype=16047#auto/zh",
            name="翻译工具")
    @WxAsyncMessage
    public void youdaofanyi(WxRequest wxRequest){
    }

    @WxButton(type =WxButton.Type.VIEW,
            group = WxButton.Group.RIGHT,
            order = WxButton.Order.SECOND,
            url = "http://www.gdou.edu.cn/",
            name="教务处")
    @WxAsyncMessage
    public void guanwang(WxRequest wxRequest){
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
        return "非常感谢 "+wxUser.getNickName() + "   /:rose";
    }

    /**
     * 接受用户文本消息，异步返回文本消息
     * @param content
     * @return the result
     */
    @WxMessageMapping(type = WxMessage.Type.TEXT)
    @WxAsyncMessage
    public String text(WxRequest wxRequest, String content) {
        log.info("wxRequest==>{}",wxRequest);
        WxSession wxSession = wxRequest.getWxSession();
        if (wxSession != null && wxSession.getAttribute("last") != null) {
            wxSession.setAttribute("last", content);
            return "上次收到消息内容为" + wxSession.getAttribute("last");
        }
        System.out.println(content);
        return "收到消息内容为" + content;
    }

    /**
     * 接受用户文本消息，同步返回图文消息
     * @param content
     * @return the result
     */
    @WxMessageMapping(type = WxMessage.Type.TEXT, wildcard = "1*")
    public WxMessage message(WxSession wxSession, String content) {
        wxSession.setAttribute("last", content);
        return WxMessage.newsBuilder()
                .addItem(WxMessageBody.News.Item.builder().title(content).description("随便一点")
                        .picUrl("http://k2.jsqq.net/uploads/allimg/1702/7_170225142233_1.png")
                        .url("http://baidu.com").build())
                .build();
    }

    /**
     * 接受用户文本消息，异步返回文本消息
     * @param content
     * @return the result
     */
    @WxMessageMapping(type = WxMessage.Type.TEXT, wildcard = "#*")
    @WxAsyncMessage
    public String text2(WxRequestBody.Text text, String content) {
        boolean match = text.getContent().equals(content);

        return "收到消息内容为" + content + "!结果匹配！" + match+"给班主任留言";
    }
    /**
     * 接受用户文本消息，异步返回文本消息
     * @param content
     * @return the result
     */
    @WxMessageMapping(type = WxMessage.Type.TEXT, wildcard = "$*")
    @WxAsyncMessage
    public String texttask(WxRequestBody.Text text, String content) {
        boolean match = text.getContent().equals(content);

        return "收到消息内容为" + content + "!结果匹配！" + match+"发布家庭作业";
    }
}
