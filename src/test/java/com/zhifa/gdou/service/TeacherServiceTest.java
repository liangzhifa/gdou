package com.zhifa.gdou.service;

import com.alibaba.fastjson.JSONObject;
import com.zhifa.gdou.config.baidu.TransApi;
import com.zhifa.gdou.mapper.TeacherMapper;
import com.zhifa.gdou.model.StudentInfoDetail;
import com.zhifa.gdou.model.Teacher;
import com.zhifa.gdou.resultEntity.RankingDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import javax.mail.internet.MimeMessage;

import java.text.SimpleDateFormat;
import java.util.List;

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
/*    StudentInfoDetail studentInfoDetail = studentInfoDetailMapper.selectByPrimaryKey(stuNum);
                                if (!ObjectUtils.isEmpty(studentInfoDetail)){
        if (!ObjectUtils.isEmpty(studentInfoDetail.getEmail())){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(testTime);
            String subject = format+" 成绩单出来啦";
            String text = "";
            *//**
             * 某次考试中 得到全班人的总分排名
             *//*
            List<RankingDTO> rankings = scoreSheetMapper.getRanking(stuNum, testTime);
            RankingDTO rk=new RankingDTO();
            for (int v = 0; v < rankings.size(); v++) {
                if (rankings.get(v).getStudentNum().equals(stuNum)){
                    rk = rankings.get(v);
                    rk.setRanking("你在的班级排名: <font color=\"blue\"> <b>"+rk.getRanking()+"</b></font>    ");
                    break;
                }
            }
            *//**
             * 日期考试中 年级排名
             *//*
            List<RankingDTO> allGradeRankings = scoreSheetMapper.getAllGradeRanking(testTime);
            for (int v = 0; v < allGradeRankings.size(); v++) {
                if (allGradeRankings.get(v).getStudentNum().equals(stuNum)){
                    String ranking = allGradeRankings.get(v).getRanking();
                    rk.setRanking(rk.getRanking()+"     年级排名：  <font color=\"blue\"> <b>"+ranking+"</b></font><br>");
                    break;
                }
            }
            *//**
             * 班级单科排名
             *//*
            List<String> courseAll = scoreSheetMapper.getCourseAll(stuNum);
            for (int v = 0; v < courseAll.size(); v++) {
                String course = courseAll.get(v);
                List<RankingDTO> singleRankings = scoreSheetMapper.getSingleRanking(stuNum, testTime, course);
                for (int b = 0; b < singleRankings.size(); b++) {
                    RankingDTO dto = singleRankings.get(b);
                    if (dto.getStudentNum().equals(stuNum)){
                        rk.setRanking(rk.getRanking()+"  "+course+":   <font color=\"HotPink\"> <b>"+dto.getRanking()+"</b></font> ");
                        break;
                    }

                }
            }
            text = "成绩详情如下 ：" + "<h3>" + rk.getRanking() + "</h3>";
            try {
                MimeMessage mimeMessage = jms.createMimeMessage();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                // 设置发信人，发信人需要和spring.mail.username配置的一样否则报错
                message.setFrom("877657682@qq.com");
                // 设置收信人
                message.setTo(studentInfoDetail.getEmail());
                // 设置主题
                message.setSubject(subject);
                // 第二个参数true表示使用HTML语言来编写邮件
                message.setText(text, true);
                jms.send(mimeMessage);
                wxEmMsg.setEmailMsgStatus("1");
                loger.info("qq邮箱 推送成功  学号={},成绩单={}",stuNum,rk.getRanking());

            } catch (Exception e) {
                loger.info("qq邮箱 推送失败 {}",e.getMessage());
            }

        }
    }*/
}