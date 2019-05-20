package com.zhifa.gdou.mapper;

import com.zhifa.gdou.model.StudentInfoDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentInfoDetailMapper {
    int deleteByPrimaryKey(String stuno);

    int insert(StudentInfoDetail record);

    int insertSelective(StudentInfoDetail record);

    StudentInfoDetail selectByPrimaryKey(String stuno);

    int updateByPrimaryKeySelective(StudentInfoDetail record);

    int updateByPrimaryKey(StudentInfoDetail record);

    /**
     * 根据老师id知道对应的班级学生信息，用作发邮件通知
     * @param teacherNum
     * @return
     */
    List<StudentInfoDetail>findStuDetailInfoByTeachNo(@Param("teacherNum") String teacherNum);
}