package com.zhifa.gdou.mapper;

import com.zhifa.gdou.model.StudentInfoDetail;

public interface StudentInfoDetailMapper {
    int deleteByPrimaryKey(String stuno);

    int insert(StudentInfoDetail record);

    int insertSelective(StudentInfoDetail record);

    StudentInfoDetail selectByPrimaryKey(String stuno);

    int updateByPrimaryKeySelective(StudentInfoDetail record);

    int updateByPrimaryKey(StudentInfoDetail record);
}