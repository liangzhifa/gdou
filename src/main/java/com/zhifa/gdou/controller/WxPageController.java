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

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/wx")
public class WxPageController {

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @RequestMapping("/main")
    public String toMain_login(HttpSession httpSession){
      //  WxSession wxSession = wxRequest.getWxSession();
        WxWebUser wxWebUser = WxWebUtils.getWxWebUserFromSession();
        String openId = wxWebUser.getOpenId();
        //查数据库判断该用户是否已经绑定了学号
        StudentInfo studentInfo = studentInfoMapper.selectByOpenId(openId);
        if (studentInfo==null){
            return "wx/userLogin";
        }
        httpSession.setAttribute("student",studentInfo);
        return "wx/main";
    }
}
