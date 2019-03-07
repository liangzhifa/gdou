package com.zhifa.gdou.utils;

import com.mxixm.fastboot.weixin.module.user.WxUser;
import com.zhifa.gdou.model.WxUserAttention;
import org.springframework.beans.BeanUtils;

public class WxBeanUtil {
    public static WxUserAttention WxUserToWxUserAttention( WxUser wxUser){
 /* wxUserAttention.setOpenid(wxUser.getOpenId());
        String nickName = wxUser.getNickName();
        wxUserAttention.setNickname(nickName);
        wxUserAttention.setHeadimgurl(wxUser.getHeadImgUrl());
        wxUserAttention.setCountry(wxUser.getCountry());
        wxUserAttention.setProvince(wxUser.getProvince());
        wxUserAttention.setCity(wxUser.getCity());
        wxUserAttention.setSex(String.valueOf(wxUser.getSex()));
        int insert = wxUserAttentionMapper.insert(wxUserAttention);
        System.out.println(wxUser.getNickName());*/
        WxUserAttention wxUserAttention=new WxUserAttention();
        BeanUtils.copyProperties(wxUser,wxUserAttention);
        wxUserAttention.setSex(String.valueOf(wxUser.getSex()));
        wxUserAttention.setOpenid(wxUser.getOpenId());
        wxUserAttention.setNickname(wxUser.getNickName());
        wxUserAttention.setHeadimgurl(wxUser.getHeadImgUrl());
        return wxUserAttention;
    }
}
