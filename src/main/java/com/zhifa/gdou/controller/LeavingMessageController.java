package com.zhifa.gdou.controller;

import com.zhifa.gdou.mapper.LeavingMessageMapper;
import com.zhifa.gdou.model.LeavingMessage;
import com.zhifa.gdou.model.Teacher;
import com.zhifa.gdou.resultEntity.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class LeavingMessageController {

    @Autowired
    private LeavingMessageMapper leavingMessageMapper;

    @RequestMapping("/postLeavingContent")
    public Object postLeavingContent(LeavingMessage leavingMessage, HttpSession httpSession){
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        leavingMessage.setCreatTime(new Date());
        leavingMessage.setStudentnum("");
        leavingMessage.setTeacherNum(teacher.getTeacherNum());
        int i = leavingMessageMapper.insert(leavingMessage);
        Map<Object,Object> map=new HashMap<>();
        if (i>0){
            map.put("code", ResultStatus.SUCCESS);
            map.put("msg","留言成功");
            return map;
        }
        map.put("code", ResultStatus.ERROR);
        map.put("msg","留言失败");
        return map;
    }
    @RequestMapping("/getTeacherLeavingContent")
    public Object getTeacherLeavingContent(HttpSession httpSession){
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        List<LeavingMessage> messages = leavingMessageMapper.getTeacherLeavingContent(teacher.getTeacherNum());
        return messages;
    }


    @RequestMapping("/deleteMsg")
    public Object deleteMsg(Integer id){
        int i = leavingMessageMapper.deleteByPrimaryKey(id);
        Map<Object,Object> map=new HashMap<>();
        if (i>0){
            map.put("code", ResultStatus.SUCCESS);
            return map;
        }
        map.put("code", ResultStatus.ERROR);
        return map;
    }

}
