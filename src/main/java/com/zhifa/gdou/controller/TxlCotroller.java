package com.zhifa.gdou.controller;

import com.mxixm.fastboot.weixin.annotation.WxController;
import com.mxixm.fastboot.weixin.util.WxWebUtils;
import com.zhifa.gdou.mapper.*;
import com.zhifa.gdou.model.StudentInfo;
import com.zhifa.gdou.model.StudentInfoDetail;
import com.zhifa.gdou.model.Teacher;
import com.zhifa.gdou.model.WxUserAttention;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@WxController
public class TxlCotroller {


    private static final Logger loger = LoggerFactory.getLogger(TxlCotroller.class);
    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Autowired
    private StudentInfoDetailMapper studentInfoDetailMapper;

    @Autowired
    private WxUserAttentionMapper wxUserAttentionMapper;


    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ClassInfoMapper classInfoMapper;


    @RequestMapping("/wx/getTxl")
    @ResponseBody
    public Object getTxl(){
        String openId = WxWebUtils.getWxWebUserFromSession().getOpenId();
        StudentInfo studentInfo = studentInfoMapper.selectByOpenId(openId);

        StringBuilder sb = new StringBuilder("");
        if (!ObjectUtils.isEmpty(studentInfo)){
            /**
             * 学生查询通讯录
             */
            List<StudentInfo> txl = studentInfoMapper.findTxl(studentInfo.getStudentnum());
            for (int i = 0; i < txl.size(); i++) {
                StudentInfo info = txl.get(i);
                String img = "https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=981a9f7576094b36db921ceb9bf71be4/0b55b319ebc4b745ea1ed6bccffc1e178b821595.jpg";
                String phone = "";
                String email = "";
                String address = "";
                WxUserAttention wxUserAttention = wxUserAttentionMapper.selectByOpenId(info.getOpenid());
                if (!ObjectUtils.isEmpty(wxUserAttention)){
                    img = wxUserAttention.getHeadimgurl();
                }
                StudentInfoDetail detail = studentInfoDetailMapper.selectByPrimaryKey(info.getStudentnum());
                if (!ObjectUtils.isEmpty(detail)){
                    phone = detail.getPhone();
                    email = detail.getEmail();
                    address = detail.getAddress();
                }
                String template = "<a href=\"javascript:void(0);\" class=\"weui-media-box weui-media-box_appmsg\">\n" +
                        "                <div class=\"weui-media-box__hd\">\n" +
                        "                    <img class=\"weui-media-box__thumb\"\n" +
                        "                         src=\""+img+"\"\n" +
                        "                         alt=\"\">\n" +
                        "                </div>\n" +
                        "                <div class=\"weui-media-box__bd\">\n" +
                        "                    <h5 class=\"weui-media-box__title\">"+info.getStudentname()+"</h5>\n" +
                        "                    <p class=\"weui-media-box__desc\">手机号码: "+phone+"</p>\n" +
                        "                    <p class=\"weui-media-box__desc\">邮箱: "+email+"</p>\n" +
                        "                    <p class=\"weui-media-box__desc\">家庭地址:"+address+"</p>\n" +
                        "                </div>\n" +
                        "            </a>";
                sb.append(template);

            }
            sb.append("<br>");
            return sb.toString();
        }
        sb = new StringBuilder("");
        Teacher teacher = teacherMapper.selectByOpenId(openId);
        if (!ObjectUtils.isEmpty(teacher)) {
            List<StudentInfo> stuInfos = classInfoMapper.findStuInfo(teacher.getTeacherNum());
            for (int i = 0; i < stuInfos.size(); i++) {

                String img = "https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=981a9f7576094b36db921ceb9bf71be4/0b55b319ebc4b745ea1ed6bccffc1e178b821595.jpg";
                String phone = "";
                String email = "";
                String address = "";
                StudentInfo info = stuInfos.get(i);
                WxUserAttention wxUserAttention = wxUserAttentionMapper.selectByOpenId(info.getOpenid());
                if (!ObjectUtils.isEmpty(wxUserAttention)){
                    img = wxUserAttention.getHeadimgurl();
                }
                StudentInfoDetail detail = studentInfoDetailMapper.selectByPrimaryKey(info.getStudentnum());
                if (!ObjectUtils.isEmpty(detail)){
                    phone = detail.getPhone();
                    email = detail.getEmail();
                    address = detail.getAddress();
                }
                String template = "<a href=\"javascript:void(0);\" class=\"weui-media-box weui-media-box_appmsg\">\n" +
                        "                <div class=\"weui-media-box__hd\">\n" +
                        "                    <img class=\"weui-media-box__thumb\"\n" +
                        "                         src=\""+img+"\"\n" +
                        "                         alt=\"\">\n" +
                        "                </div>\n" +
                        "                <div class=\"weui-media-box__bd\">\n" +
                        "                    <h5 class=\"weui-media-box__title\">"+info.getStudentname()+"</h5>\n" +
                        "                    <p class=\"weui-media-box__desc\">手机号码: "+phone+"</p>\n" +
                        "                    <p class=\"weui-media-box__desc\">邮箱: "+email+"</p>\n" +
                        "                    <p class=\"weui-media-box__desc\">家庭地址:"+address+"</p>\n" +
                        "                </div>\n" +
                        "            </a>";
                sb.append(template);
            }
            sb.append("<br>");
            return sb.toString();
        }

        return "";
    }
}
