package com.zhifa.gdou.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhifa.gdou.mapper.ClassInfoMapper;
import com.zhifa.gdou.mapper.TeacherMapper;
import com.zhifa.gdou.model.Teacher;
import com.zhifa.gdou.resultEntity.LayUIDataGrid;
import com.zhifa.gdou.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    ClassInfoMapper classInfoMapper;

    @RequestMapping(value = "/findAllTeachers")
    public LayUIDataGrid findAllTeachers(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit",defaultValue = "80")Integer limit,
                                         @RequestParam(value = "teacherName",defaultValue = "",required = false) String teacherName){
        PageHelper.startPage(page,limit);//分页插件
        teacherName="%"+teacherName+"%";
        List<Teacher> teachers = teacherService.findAllTeachers(teacherName);
        PageInfo<Teacher>info=new PageInfo<>(teachers);
        long total = info.getTotal();
        List<Teacher> list = info.getList();
       for(Teacher teacher:list){
            String teacherNum = teacher.getTeacherNum();
            String name = classInfoMapper.findClassNameByheadMasterNum(teacherNum);
            if (name!=null){
                teacher.setClassName(name);
            }
        }
        return LayUIDataGrid.ReturnDataGrid(total,list);
    }

    @RequestMapping("/deleteById")
    public Object deleteById(Integer id){
        int n=teacherService.deleteById(id);
        Map<String,Object> map=new HashMap<>();
        if (n>0){
            map.put("status",true);
            return map;
        }
        map.put("status",false);
        map.put("msg","删除失败，请重试！");
        return map;
    }

    @RequestMapping("/insertTeacherInfo")
    public Object insertTeacherInfo(String teacherName){
        Map<String,Object> map=new HashMap<>();
        Teacher teacher=new Teacher();
        teacher.setTeacherName(teacherName);
        String teacherNumAndPass="gdou"+teacherName.hashCode();
        teacher.setTeacherNum(teacherNumAndPass);
        teacher.setTeacherPassword(teacherNumAndPass);
        teacher.setState(Byte.valueOf("1"));
        int insert = teacherMapper.insert(teacher);
        if (insert>0){
            map.put("code", 0);
            map.put("msg","添加成功！");
            return map;
        }
        map.put("code", 1);
        map.put("msg","失败！请重试。");

        return map;
    }
    @RequestMapping("/modify")
    public Object modify(String className,String teacherPassword,String teacherNum,Integer id){
        Map<String,Object> map=new HashMap<>();
        int i=0,j=0;
        if (!ObjectUtils.isEmpty(teacherPassword)){
            Teacher teacher = teacherMapper.selectByPrimaryKey(id);
            teacher.setTeacherPassword(teacherPassword);
            i = teacherMapper.updateByPrimaryKeySelective(teacher);
            if (i<=0){
                throw new RuntimeException();
            }
        }
        if (!ObjectUtils.isEmpty(className)){
            j = classInfoMapper.updatehead_master_numByclass_name(className, teacherNum);
        }
        map.put("code", 0);
        map.put("msg","成功！");
        return map;
    }

}
