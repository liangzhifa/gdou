package com.zhifa.gdou.service;

import com.zhifa.gdou.mapper.TeacherMapper;
import com.zhifa.gdou.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    public List<Teacher> findAllTeachers(String teacherName){
        return  teacherMapper.findAll(teacherName);
    }

    public int deleteById(Integer id) {
        int i = teacherMapper.UpdateTeacherState(id, new Byte("1"));
        System.out.println();
        return i;
    }
}
