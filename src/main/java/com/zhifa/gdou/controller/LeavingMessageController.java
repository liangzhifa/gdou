package com.zhifa.gdou.controller;

import com.zhifa.gdou.mapper.LeavingMessageMapper;
import com.zhifa.gdou.mapper.StudentInfoDetailMapper;
import com.zhifa.gdou.model.LeavingMessage;
import com.zhifa.gdou.model.StudentInfoDetail;
import com.zhifa.gdou.model.Teacher;
import com.zhifa.gdou.resultEntity.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author: zhifa
 * @Date: 2019/5/20
 * @Description: 教师留言信息
 */
@RestController
@RequestMapping("/wx")
public class LeavingMessageController {

    @Autowired
    private LeavingMessageMapper leavingMessageMapper;

    @Autowired
    private StudentInfoDetailMapper studentInfoDetailMapper;


    private static final Logger loger = LoggerFactory.getLogger(LeavingMessageController.class);



    /**
     * @Author: zhifa
     * @Date: 2019/5/18
     * @Description: qq邮箱推送
     */
    @Autowired
    private JavaMailSender jms;

    @RequestMapping("/postLeavingContent")
    public Object postLeavingContent(LeavingMessage leavingMessage, HttpSession httpSession){
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        Date date = new Date();
        leavingMessage.setCreatTime(date);
        leavingMessage.setStudentnum("");
        leavingMessage.setTeacherNum(teacher.getTeacherNum());

        int i = leavingMessageMapper.insert(leavingMessage);

        Map<Object,Object> map=new HashMap<>();
        if (i>0){
            map.put("code", ResultStatus.SUCCESS);
            map.put("msg","留言成功");
            /**
             * fasong 发送邮件通知
             */
            List<StudentInfoDetail> detailInfos = studentInfoDetailMapper.findStuDetailInfoByTeachNo(teacher.getTeacherNum());
            for (int k = 0; k < detailInfos.size(); k++) {
                StudentInfoDetail detail = detailInfos.get(k);
                if (!ObjectUtils.isEmpty(detail)&&!ObjectUtils.isEmpty(detail.getEmail())){
                    try {
                        String subject = "老师最新留言";
                        String text = "";

                        String leavingType = leavingMessage.getType();
                        String content = leavingMessage.getContent();
                        MimeMessage mimeMessage = jms.createMimeMessage();
                        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                        // 设置发信人，发信人需要和spring.mail.username配置的一样否则报错
                        message.setFrom("877657682@qq.com");
                        // 设置收信人
                        message.setTo(detail.getEmail());
                        // 设置主题
                        message.setSubject(subject);
                        // 第二个参数true表示使用HTML语言来编写邮件

                        text+="时间: <font color=\"HotPink\"> <b>"+ new SimpleDateFormat("yyyy-MM-dd").format(date)+"</b></font> <br>";
                        text+="留言类型: <font color=\"#4876FF\"> <b>"+leavingType+"</b></font><br> ";
                        text+="内容:  <font color=\"#EE1289\"> <b>"+content+"</b></font>";
                        message.setText(text, true);
                        jms.send(mimeMessage);
                        loger.info("qq邮箱 推送成功到学号={}",detail.getStuno());

                    } catch (Exception e) {
                        loger.info("qq邮箱 推送失败 {}",e.getMessage());
                    }
                }

            }
            return map;
        }
        map.put("code", ResultStatus.ERROR);
        map.put("msg","留言失败");
        return map;
    }
    @RequestMapping("/getTeacherLeavingContent")
    public Object getTeacherLeavingContent(HttpSession httpSession){
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        List<LeavingMessage> messages = leavingMessageMapper.getTeacherLeavingContent(teacher.getTeacherNum());
        return messages;
    }


    @RequestMapping("/deleteMsg")
    public Object deleteMsg(Integer id){
        int i = leavingMessageMapper.deleteByPrimaryKey(id);
        Map<Object,Object> map=new HashMap<>();
        if (i>0){
            map.put("code", ResultStatus.SUCCESS);
            return map;
        }
        map.put("code", ResultStatus.ERROR);
        return map;
    }

    private void sendEmail(){

    }

}
