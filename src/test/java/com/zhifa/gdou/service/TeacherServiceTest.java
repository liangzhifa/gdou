package com.zhifa.gdou.service;

import com.zhifa.gdou.mapper.TeacherMapper;
import com.zhifa.gdou.model.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherServiceTest {

    @Autowired
    TeacherMapper teacherMapper;

    @Test
    public void insertTest(){
        for (int i = 0; i <100 ; i++) {
            Teacher teacher=new Teacher();
            teacher.setTeacherName("用户名"+i);
            teacher.setTeacherPassword("密码"+i);
            teacherMapper.insert(teacher);
        }
    }

}