package com.zhifa.gdou.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.mxixm.fastboot.weixin.annotation.WxController;
import com.mxixm.fastboot.weixin.module.message.WxMessage;
import com.mxixm.fastboot.weixin.module.message.WxMessageBody;
import com.mxixm.fastboot.weixin.module.message.WxMessageTemplate;
import com.mxixm.fastboot.weixin.module.message.WxUserMessage;
import com.mxixm.fastboot.weixin.web.WxWebUser;
import com.zhifa.gdou.mapper.WxImageInfoMapper;
import com.zhifa.gdou.utils.ImagesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WxController
public class WxImageController {

    private static Logger log = LoggerFactory.getLogger(WxImageController.class);

    @Autowired
    private WxImageInfoMapper wxImageInfoMapper;

    @Autowired
    private WxMessageTemplate wxMessageTemplate;

















   /* @RequestMapping("/wx/imageRes")
    public Object getImageRes(HttpSession httpSession){

        if (true){


            //Collection<String> toUsers = new ArrayList<>();
            //toUsers.add("ovwMI59y1dfGKq2kJ9yDn96-kUPM");
            WxUserMessage build = WxMessage.newsBuilder()
                    .addItem(WxMessageBody.News.Item.builder().title("111").description("随便一点")
                            .picUrl("http://mmbiz.qpic.cn/mmbiz_jpg/BXa2ick0Zc8mhZBGSicbe5xd8q1vbESAWOjjOKw4icggiaZlIUP0Woj8FibWHp8yeVLvCJbwg46BfQLCPUlf8WeCib4g/0")
                            .url("http://baidu.com").build())
                    .build();
            wxMessageTemplate.sendMessage("ovwMI59y1dfGKq2kJ9yDn96-kUPM",build);  //setToUser("ovwMI59y1dfGKq2kJ9yDn96-kUPM");


            return "";
        }




        WxWebUser wx_session_user = (WxWebUser) httpSession.getAttribute("WX_SESSION_USER");
        String openId = wx_session_user.getOpenId();
        String picUrl = wxImageInfoMapper.getPicUrlByOpenId(openId);
        log.info("openId={}",openId);

        Map<String, Object> map = new HashMap<>();
        if (!ObjectUtils.isEmpty(picUrl)){
            // 初始化一个AipImageClassify
            // 通用文字识别, 图片参数为远程url图片
            byte[] fileStream = ImagesUtils.getFileStream(picUrl);
            JSONObject resObj = JSONObject.parseObject(client.advancedGeneral(fileStream, new HashMap<String, String>()).toString(2));

            JSONArray data = new JSONArray();
            map.put("picUrl",picUrl);
            JSONArray result =  resObj.getJSONArray("result");
            for (int i = 0; i <result.size() ; i++) {
                JSONObject jsonObject = result.getJSONObject(i);
                jsonObject.put("const", "const");
                jsonObject.put("type", jsonObject.getString("root"));
                jsonObject.put("money", jsonObject.getDouble("score")*100);
                data.add(jsonObject);
            }
            map.put("data", data);
            return  map;
        }
        return "";
    }
*/

}
