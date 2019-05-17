package com.zhifa.gdou.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhifa.gdou.mapper.ClassInfoMapper;
import com.zhifa.gdou.mapper.ScoreSheetMapper;
import com.zhifa.gdou.mapper.StudentInfoMapper;
import com.zhifa.gdou.mapper.TeacherMapper;
import com.zhifa.gdou.model.ClassInfo;
import com.zhifa.gdou.model.Teacher;
import com.zhifa.gdou.resultEntity.LayUIDataGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ClassInfoController {
    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Autowired
    private StudentInfoMapper studentInfoMapper;
    @Autowired
    private ScoreSheetMapper scoreSheetMapper;

    @Autowired
    TeacherMapper teacherMapper;


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


    //后台管理页面中的学生管理查询列表
    @RequestMapping(value = "/classInfo/findAllclassInfos")
    public LayUIDataGrid findAllclassInfos(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit",defaultValue = "80")Integer limit,
                                         @RequestParam(value = "teacherName",defaultValue = "",required = false) String className){
        PageHelper.startPage(page,limit);//分页插件
        className="%"+className+"%";
        List<ClassInfo> classInfos = classInfoMapper.findAllStudents(className);
        PageInfo<ClassInfo> info=new PageInfo<>(classInfos);
        long total = info.getTotal();
        List<ClassInfo> list = info.getList();
        for (int i = 0; i < list.size(); i++) {
            ClassInfo classInfo = list.get(i);
            String headMasterNum = classInfo.getHeadMasterNum();
            if (!StringUtils.isEmpty(headMasterNum)){
                String name = teacherMapper.selectTeacherNameByNo(headMasterNum);
                classInfo.setHeadMasterNum(name);
            }
        }
        return LayUIDataGrid.ReturnDataGrid(total,list);
    }



    @RequestMapping("/classInfo/insertclassInfo")
    public Object insertTeacherInfo(ClassInfo classInfo){
        Map<String,Object> map=new HashMap<>();
        int insert = classInfoMapper.insertSelective(classInfo);
        if (insert>0){
            map.put("code", 0);
            map.put("msg","添加成功！");
            return map;
        }
        map.put("code", 1);
        map.put("msg","失败！请重试。");

        return map;
    }


    @RequestMapping("/classInfo/deleteById")
    public Object deleteById(Integer id){

        ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(id);
        List<ClassInfo>lists=classInfoMapper.findClassInfosByClassName(classInfo.getClassName());
        int n=0;
        Map<String,Object> map=new HashMap<>();
        if (lists.size()==1){
            classInfoMapper.deleteByClassName(classInfo.getClassName());
            map.put("status",true);
            return map;
        } else {
            map.put("status",false);
            map.put("msg","班级下面有学生，不能删除！");
            return map;
        }

    }





    @RequestMapping("/classInfo/modify")
    @Transactional
    public Object modify(String className,Integer classNum,Integer id){
        ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(id);
        String oldName = classInfo.getClassName();
        Integer oldNum = classInfo.getClassNum();

        classInfoMapper.updateInfoByclassName(className, oldName);
        classInfoMapper.updateInfoByclassNum(classNum, oldNum);
       /* classInfo.setClassName(className);
        classInfo.setClassNum(classNum);
        int i = classInfoMapper.updateByPrimaryKeySelective(classInfo);*/
        Map<String,Object> map=new HashMap<>();
        map.put("code", 0);
        map.put("msg","成功！");
        return map;
    }
}
