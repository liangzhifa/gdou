package com.zhifa.gdou.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhifa.gdou.mapper.ClassInfoMapper;
import com.zhifa.gdou.mapper.ScoreSheetMapper;
import com.zhifa.gdou.mapper.StudentInfoMapper;
import com.zhifa.gdou.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ClassInfoController {
    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Autowired
    private StudentInfoMapper studentInfoMapper;
    @Autowired
    private ScoreSheetMapper scoreSheetMapper;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
    }

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

    @RequestMapping("/classInfo/getStudentInfo")
    public Object getStudentInfo(Date testTime, HttpSession session){

        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (ObjectUtils.isEmpty(teacher)){
            return "";
        }
        String teacherNum = teacher.getTeacherNum();
        List<String> stuNum = classInfoMapper.findStuNum(teacherNum);
        List<String> stuNumsByDate = scoreSheetMapper.findStuNumsByDate(testTime);
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","请选择");
        jsonObject.put("id","");
        jsonArray.add(jsonObject);
        for (String student:stuNum){
            if (stuNumsByDate.contains(student)){
                continue;
            }
            jsonObject=new JSONObject();
            String name = studentInfoMapper.findNameByNum(student);
            jsonObject.put("name",student+" "+name);
            jsonObject.put("id",student);
            jsonArray.add(jsonObject);
        }
        return jsonArray;

    }


}
