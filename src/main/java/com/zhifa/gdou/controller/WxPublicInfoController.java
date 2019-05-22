package com.zhifa.gdou.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhifa.gdou.mapper.WxMoreInfoMapper;
import com.zhifa.gdou.model.WxMoreInfo;
import com.zhifa.gdou.resultEntity.LayUIDataGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WxPublicInfoController {

    private static final Logger loger = LoggerFactory.getLogger(WxPublicInfoController.class);

    @Autowired
    private WxMoreInfoMapper wxMoreInfoMapper;

    //后台管理页面中的学生管理查询列表
    @RequestMapping(value = "/wxMoreInfo/findAllWxMoreInfos")
    public LayUIDataGrid findAllInfos(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "limit", defaultValue = "80") Integer limit) {

        PageHelper.startPage(page, limit);//分页插件
        List<WxMoreInfo> infos = wxMoreInfoMapper.getAll();
        PageInfo<WxMoreInfo> info = new PageInfo<>(infos);
        long total = info.getTotal();
        List<WxMoreInfo> list = info.getList();
        return LayUIDataGrid.ReturnDataGrid(total, list);
    }

    /**
     * 后台管理  添加
     *
     * @param
     * @return
     */
    @RequestMapping("/wxMoreInfo/insertWxMoreInfo")
    public Object insertTeacherInfo(WxMoreInfo wxMoreInfo) {
        Map<String, Object> map = new HashMap<>();

        int insert = wxMoreInfoMapper.insert(wxMoreInfo);
        if (insert > 0) {
            map.put("code", 0);
            map.put("msg", "添加成功！");

            return map;
        }
        map.put("code", 1);
        map.put("msg", "失败！请重试。");
        return map;
    }


    @RequestMapping("/wxMoreInfo/modify")
    public Object modify(WxMoreInfo wxMoreInfo) {
        Map<String, Object> map = new HashMap<>();
        int i = wxMoreInfoMapper.updateByPrimaryKey(wxMoreInfo);
        map.put("code", 0);
        map.put("msg", "修改成功！");
        return map;

    }


    /**
     * 删除信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/wxMoreInfo/deleteById")
    public Object deleteById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        int n = wxMoreInfoMapper.deleteByPrimaryKey(id);
        if (n > 0) {
            map.put("status", true);
            map.put("msg", "操作成功");
            return map;
        }
        map.put("status", false);
        map.put("msg", "删除失败，请重试！");
        return map;

    }


}
