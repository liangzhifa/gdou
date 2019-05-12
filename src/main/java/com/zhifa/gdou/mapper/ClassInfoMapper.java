package com.zhifa.gdou.mapper;

import com.zhifa.gdou.model.ClassInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassInfo record);

    int insertSelective(ClassInfo record);

    ClassInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassInfo record);

    int updateByPrimaryKey(ClassInfo record);

    String findClassNameByheadMasterNum(@Param("headMasterNum") String headMasterNum);

    List<String> findAllClassName();

    /*
    * 修改班主任
    * */
    int updatehead_master_numByclass_name(@Param("class_name") String class_name, @Param("head_master_num") String head_master_num);

    /*根据班主任id 查询班主任下面的学生
    * */
    List<String>findStuNum(@Param("headMasterNum") String headMasterNum);

    String findTeacherNumByStuNum(@Param("studentNum") String studentNum);

    List<ClassInfo> findAllStudents(@Param("className") String className);

    String selcetClassNameByStuNo(@Param("studentnum") String studentnum);

    int updateClassNameByStuNo(@Param("className") String className,@Param("studentnum") String studentnum );

    ClassInfo findClassByClassName(@Param("className") String className);

    ClassInfo findClassByStuNo(@Param("studentnum") String studentnum);

    List<ClassInfo> findClassInfosByClassName(@Param("className") String className);

    int deleteByClassName(@Param("className") String className);

    int deleteClassInfoByStuNo(@Param("studentnum") String studentnum);
}