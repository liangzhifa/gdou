package com.zhifa.gdou.controller;

import com.mxixm.fastboot.weixin.annotation.WxController;
import com.mxixm.fastboot.weixin.util.WxWebUtils;
import com.mxixm.fastboot.weixin.web.WxWebUser;
import com.zhifa.gdou.mapper.StudentInfoMapper;
import com.zhifa.gdou.mapper.TeacherMapper;
import com.zhifa.gdou.model.StudentInfo;
import com.zhifa.gdou.model.Teacher;
import com.zhifa.gdou.resultEntity.ResultStatus;
import org.apache.commons.codec.digest.Md5Crypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
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


    private static final Logger loger = LoggerFactory.getLogger(WxStudentLogin.class);

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @RequestMapping("/wx/student_login")
    public Object student_login(@RequestParam("studentNum") String studentNum,
                                @RequestParam("studentPass") String studentPass,
                                HttpSession session){
        studentPass = DigestUtils.md5DigestAsHex(studentPass.getBytes());
        loger.info("登录密码：{}",studentPass);
        WxWebUser wxWebUser = WxWebUtils.getWxWebUserFromSession();
        StudentInfo studentInfo = studentInfoMapper.selectByStudentNumAndStudentPass(studentNum, studentPass);
        Teacher teacher = teacherMapper.selectByNameAndPass(studentNum, studentPass);
        Map<Object,Object>map=new HashMap<>();

        if (studentInfo!=null){
            if(!StringUtils.isEmpty(studentInfo.getOpenid())){
                map.put("code", ResultStatus.ERROR);
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
                return map;
            }
        }if (teacher!=null){
            if(!StringUtils.isEmpty(teacher.getOpenid())){
                map.put("code", ResultStatus.ERROR);
                map.put("msg","该账户已经绑定其他教师");
                return map;
            }
            teacher.setOpenid(wxWebUser.getOpenId());
            int i = teacherMapper.updateByPrimaryKeySelective(teacher);
            if (i>0){
                session.setAttribute("teacher",teacher);
                map.put("code", ResultStatus.SUCCESS);
                map.put("msg","登录成功");
                map.put("url","/wx/main");
                return map;
            }
        }
        if (studentInfo==null||teacher==null){
            map.put("code", ResultStatus.ERROR);
            map.put("msg","账号或者密码不正确");
        }
        return map;
    }

}
