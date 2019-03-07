package com.zhifa.gdou.mapper;

import com.zhifa.gdou.model.StudentInfo;
import org.apache.ibatis.annotations.Param;

public interface StudentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentInfo record);

    int insertSelective(StudentInfo record);

    StudentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentInfo record);

    int updateByPrimaryKey(StudentInfo record);

    StudentInfo selectByOpenId(@Param("openId") String openId);

    StudentInfo selectByStudentNumAndStudentPass(@Param("studentNum") String studentNum, @Param("studentPass") String studentPass);


}