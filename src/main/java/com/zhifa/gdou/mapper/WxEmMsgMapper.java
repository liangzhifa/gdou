package com.zhifa.gdou.mapper;

import com.zhifa.gdou.model.WxEmMsg;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface WxEmMsgMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(WxEmMsg record);

    int insertSelective(WxEmMsg record);

    WxEmMsg selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(WxEmMsg record);

    int updateByPrimaryKey(WxEmMsg record);


    WxEmMsg findMgsByOpenIdAndDate(@Param("openId") String openId, @Param("test_time") Date test_time);
}