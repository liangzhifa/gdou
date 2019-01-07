package com.zhifa.gdou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.zhifa.gdou.mapper"})
public class GdouApplication {

    public static void main(String[] args) {


        SpringApplication.run(GdouApplication.class, args);
        System.out.println("======================完成========================");

    }
/**
 * 基于SSM的中小学家校服务系统的设计与实现
 *
 * 项目主要功能： 中小学家校服务系统是一套集教师管理，学生管理，家长管理
 * 并连接到微信公众平台实时发布消息的系统。
 * 系统管理员可以管理教师的增删查改，教师对应于一个班的学生，而学生又对应着各自的家长
 * 教师可以录入学生的信息，录入学生个人考试成绩并进行统计，添加课后作业，
 * 并添加该学生家长的账号便于通过微信公众平台进行联系，教师也可以通过本系统发布消息到公众平台。
 * 家长通过老师给的账号登陆本系统可以查看学生的个人信息，考试成绩，并给老师留言。
 */
}

