package com.zhifa.gdou.mapper;

import com.zhifa.gdou.model.WxImageInfo;
import org.apache.ibatis.annotations.Param;

public interface WxImageInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxImageInfo record);

    int insertSelective(WxImageInfo record);

    WxImageInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxImageInfo record);

    int updateByPrimaryKey(WxImageInfo record);

    String getPicUrlByOpenId(@Param("openid") String openid);
}