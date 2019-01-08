package com.zhifa.gdou.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhifa.gdou.model.Teacher;
import com.zhifa.gdou.resultEntity.LayUIDataGrid;
import com.zhifa.gdou.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

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

}
