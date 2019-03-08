package com.zhifa.gdou.mapper;

import com.zhifa.gdou.model.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
    /**
     * genju根据条件查询教师
     * @param teacherName
     * @return
     */
    List<Teacher> findAll(@Param(value = "teacherName") String teacherName);
}