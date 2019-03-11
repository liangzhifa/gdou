package com.zhifa.gdou.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhifa.gdou.mapper.ClassInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClassInfoController {
    @Autowired
    private ClassInfoMapper classInfoMapper;

    @RequestMapping("/classInfo/getClassName")
    public Object findAllClassName(){
        List<String> allClassName = classInfoMapper.findAllClassName();
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","请选择");
        jsonObject.put("id","");
        jsonArray.add(jsonObject);
        for (String name:allClassName){
            jsonObject=new JSONObject();
            jsonObject.put("name",name);
            jsonObject.put("id",name);
            jsonArray.add(jsonObject);
        }
         return jsonArray;
    }

}
