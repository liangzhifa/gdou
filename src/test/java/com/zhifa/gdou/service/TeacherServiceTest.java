package com.zhifa.gdou.service;

import com.alibaba.fastjson.JSONObject;
import com.zhifa.gdou.config.baidu.TransApi;
import com.zhifa.gdou.mapper.TeacherMapper;
import com.zhifa.gdou.model.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherServiceTest {

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    private JavaMailSender jms;

    @Autowired
    TransApi transApi;

    @Test
    public void insertTest() {
        for (int i = 0; i < 100; i++) {
            Teacher teacher = new Teacher();
            teacher.setTeacherName("用户名" + i);
            teacher.setTeacherPassword("密码" + i);
            teacherMapper.insert(teacher);
        }
    }

    @Test
    public void translate() {
        String query = "你好啊，今天天气很好！";
        JSONObject stranslate = JSONObject.parseObject(transApi.getTransResult(query, "auto", "en"));
        String stranslateRes = stranslate.getJSONArray("trans_result").getJSONObject(0).getString("dst");
        System.out.println(stranslateRes);
    }

     @Test                      // 收信人     // 设置主题    // 设置内容
    public void sendMail() {
        String subject = "感谢的您注册~ ";
         String text = "";
        text = "感谢你的注册，你的默认密码是：" + "<h3>" + text + "</h3>";
        try {
            MimeMessage mimeMessage = jms.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            // 设置发信人，发信人需要和spring.mail.username配置的一样否则报错
            message.setFrom("877657682@qq.com");
            // 设置收信人
            message.setTo("877657682@qq.com");
            // 设置主题
            message.setSubject(subject);
            // 第二个参数true表示使用HTML语言来编写邮件
            message.setText(text, true);
            jms.send(mimeMessage);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }


}