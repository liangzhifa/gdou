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

    int UpdateTeacherState(@Param("id") Integer id, @Param("state") Byte state);

    Teacher selectByNameAndPass(@Param("teacherNum") String teacherNum, @Param("teacherPassword") String teacherPassword);

    //教师登录
    Teacher selectByOpenId(@Param("openId") String openId);


    int updateOpenIdByOpenId(@Param("openId") String openId);
}