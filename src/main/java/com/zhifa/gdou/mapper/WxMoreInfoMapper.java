package com.zhifa.gdou.mapper;

import com.zhifa.gdou.model.WxMoreInfo;

import java.util.List;

public interface WxMoreInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxMoreInfo record);

    int insertSelective(WxMoreInfo record);

    WxMoreInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxMoreInfo record);

    int updateByPrimaryKey(WxMoreInfo record);

    List<WxMoreInfo>getAll();

}