package com.zhifa.gdou.controller;

import com.mxixm.fastboot.weixin.annotation.WxController;
import com.mxixm.fastboot.weixin.util.WxWebUtils;
import com.mxixm.fastboot.weixin.web.WxWebUser;
import com.zhifa.gdou.mapper.StudentInfoMapper;
import com.zhifa.gdou.model.StudentInfo;
import com.zhifa.gdou.resultEntity.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WxController
@ResponseBody
public class WxStudentLogin {

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @RequestMapping("/wx/student_login")
    public Object student_login(@RequestParam("studentNum") String studentNum,
                                @RequestParam("studentPass") String studentPass,
                                HttpSession session){
        WxWebUser wxWebUser = WxWebUtils.getWxWebUserFromSession();
        StudentInfo studentInfo = studentInfoMapper.selectByStudentNumAndStudentPass(studentNum, studentPass);
        Map<Object,Object>map=new HashMap<>();
        map.put("code", ResultStatus.ERROR);
        map.put("msg","学号或者密码不正确");
        if (studentInfo==null){
            return map;
        }
        if(!StringUtils.isEmpty(studentInfo.getOpenid())){
            map.put("msg","该账户已经绑定其他用户");
            return map;
        }
        studentInfo.setOpenid(wxWebUser.getOpenId());
        int i=studentInfoMapper.updateByPrimaryKeySelective(studentInfo);
        if (i>0){
            session.setAttribute("student",studentInfo);
            map.put("code", ResultStatus.SUCCESS);
            map.put("msg","登录成功");
            map.put("url","/wx/main");
        }

        return map;
    }

}
