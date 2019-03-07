package com.zhifa.gdou.controller;

import com.mxixm.fastboot.weixin.module.web.WxRequest;
import com.mxixm.fastboot.weixin.module.web.session.WxSession;
import com.mxixm.fastboot.weixin.util.WxWebUtils;
import com.mxixm.fastboot.weixin.web.WxWebUser;
import com.zhifa.gdou.mapper.StudentInfoMapper;
import com.zhifa.gdou.model.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wx")
public class WxPageController {

    @Autowired
    StudentInfoMapper studentInfoMapper;

    @RequestMapping("/main")
    public String toMain_login(){
      //  WxSession wxSession = wxRequest.getWxSession();
        WxWebUser wxWebUser = WxWebUtils.getWxWebUserFromSession();
        String openId = wxWebUser.getOpenId();
        //todo cha查数据库判断该用户是否已经绑定了学号
        StudentInfo studentInfo = studentInfoMapper.selectByOpenId(openId);
        if (studentInfo==null){
            return "wx/userLogin";
        }
        return "wx/main";
    }
}
