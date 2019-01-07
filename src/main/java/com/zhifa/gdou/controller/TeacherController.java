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

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @RequestMapping("/findAllTeachers")
    public LayUIDataGrid findAllTeachers(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit",defaultValue = "80")Integer limit){

        PageHelper.startPage(page,limit);
        List<Teacher> teachers = teacherService.findAllTeachers();
        PageInfo<Teacher>info=new PageInfo<>(teachers);
        long total = info.getTotal();
        List<Teacher> list = info.getList();
        return new LayUIDataGrid(total,list);
    }

}
