package com.zhifa.gdou.mapper;

import com.zhifa.gdou.model.Manager;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);

    @Select("select * from manager")
    List<Manager> findAll();
}