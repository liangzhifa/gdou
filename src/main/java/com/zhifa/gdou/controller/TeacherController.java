package com.zhifa.gdou.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhifa.gdou.mapper.ClassInfoMapper;
import com.zhifa.gdou.mapper.TeacherMapper;
import com.zhifa.gdou.model.Teacher;
import com.zhifa.gdou.resultEntity.LayUIDataGrid;
import com.zhifa.gdou.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhifa
 * @Date: 2019/5/20
 * @Description:  管理员对的教师后台管理
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private static final Logger loger = LoggerFactory.getLogger(TeacherController.class);


    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ClassInfoMapper classInfoMapper;

    @RequestMapping("/login")
    public Object login(String teacherNum, String teacherPassword, HttpSession httpSession){
        Map<String,Object> map=new HashMap<>();
        if (ObjectUtils.isEmpty(teacherNum)||ObjectUtils.isEmpty(teacherPassword)){
            map.put("code", 1);
            map.put("msg","账号、密码不能空");
            return map;
        }
        teacherPassword = DigestUtils.md5DigestAsHex(teacherPassword.getBytes());
        loger.info("登录密码：{}",teacherPassword);
        Teacher teacher = teacherMapper.selectByNameAndPass(teacherNum, teacherPassword);
        if (teacher==null){
            map.put("code", 1);
            map.put("msg","账号、密码不正确");
            return map;
        }
        map.put("code", 0);
        map.put("url","/main.html");
        httpSession.setAttribute("teacher",teacher);
        return map;

    }

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
    public Object insertTeacherInfo(String teacherName,String teacherPassword){
        Map<String,Object> map=new HashMap<>();
        Teacher teacher=new Teacher();
        teacher.setTeacherName(teacherName);
        String teacherNumAndPass="gdou"+teacherName.hashCode();
        teacher.setTeacherNum(teacherNumAndPass);
        if (ObjectUtils.isEmpty(teacherPassword)){
            teacherPassword=teacherNumAndPass;
        }
        teacher.setTeacherPassword(DigestUtils.md5DigestAsHex(teacherPassword.getBytes()));
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
    @Transactional
    public Object modify(String className,String teacherPassword,String teacherNum,Integer id){
        Map<String,Object> map=new HashMap<>();
        int i=0,j=0;
        if (!ObjectUtils.isEmpty(teacherPassword)){
            Teacher teacher = teacherMapper.selectByPrimaryKey(id);
            teacherPassword = DigestUtils.md5DigestAsHex(teacherPassword.getBytes());
            teacher.setTeacherPassword(teacherPassword);
            loger.info("修改登录密码：{}",teacherPassword);
            i = teacherMapper.updateByPrimaryKeySelective(teacher);
            if (i<=0){
                throw new RuntimeException();
            }
        }
        if (!ObjectUtils.isEmpty(className)){
            classInfoMapper.updateHead_Tescher(teacherNum);
            classInfoMapper.updatehead_master_numByclass_name(className, teacherNum);
        }
        map.put("code", 0);
        map.put("msg","成功！");
        return map;
    }



}
