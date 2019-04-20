package com.zhifa.gdou.mapper;


import com.zhifa.gdou.model.LeavingMessage;

import java.util.List;

public interface LeavingMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeavingMessage record);

    int insertSelective(LeavingMessage record);

    LeavingMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LeavingMessage record);

    int updateByPrimaryKey(LeavingMessage record);

    List<LeavingMessage> getTeacherLeavingContent(String teacher_num);
}