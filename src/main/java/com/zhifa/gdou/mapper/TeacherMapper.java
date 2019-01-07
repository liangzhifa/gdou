package com.zhifa.gdou.mapper;

import com.zhifa.gdou.model.Teacher;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    @Select(value = " select * from teacher")
    List<Teacher> findAll();
}