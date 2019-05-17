package com.zhifa.gdou.controller;

import com.mxixm.fastboot.weixin.module.web.WxRequest;
import com.mxixm.fastboot.weixin.util.WxWebUtils;
import com.mxixm.fastboot.weixin.web.WxWebUser;
import com.zhifa.gdou.mapper.StudentInfoMapper;
import com.zhifa.gdou.mapper.TeacherMapper;
import com.zhifa.gdou.model.StudentInfo;
import com.zhifa.gdou.model.Teacher;
import com.zhifa.gdou.resultEntity.ImgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/wx")
public class WxPageController {


    private static final Logger loger = LoggerFactory.getLogger(WxPageController.class);


    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @RequestMapping("/main")
    public String toMain_login(HttpSession httpSession){
      //  WxSession wxSession = wxRequest.getWxSession();
        WxWebUser wxWebUser = WxWebUtils.getWxWebUserFromSession();
        String openId = wxWebUser.getOpenId();
        //查数据库判断该用户是否已经绑定了学号
        StudentInfo studentInfo = studentInfoMapper.selectByOpenId(openId);
        Teacher teacher = teacherMapper.selectByOpenId(openId);


        if (studentInfo!=null){
            httpSession.setAttribute("student",studentInfo);
            return "wx/main";
        }if (teacher!=null){
            httpSession.setAttribute("teacher",teacher);
            return "wx/wx_teacher";
        }
        return "wx/userLogin";

    }
    @RequestMapping("/wx_teacher")
    public String toWx_teacher(){
        return "wx/wx_teacher";
    }


    @RequestMapping("/imagesRes")
    public String imagesRes(){
        return "wx/imagesRes";
    }

}
