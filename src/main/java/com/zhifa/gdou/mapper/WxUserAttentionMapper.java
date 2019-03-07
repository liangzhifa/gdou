package com.zhifa.gdou.mapper;

import com.zhifa.gdou.model.WxUserAttention;
import org.apache.ibatis.annotations.Param;

public interface WxUserAttentionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxUserAttention record);

    int insertSelective(WxUserAttention record);

    WxUserAttention selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxUserAttention record);

    int updateByPrimaryKeyWithBLOBs(WxUserAttention record);

    int updateByPrimaryKey(WxUserAttention record);

    WxUserAttention selectByOpenId(@Param("openId") String openId);
}