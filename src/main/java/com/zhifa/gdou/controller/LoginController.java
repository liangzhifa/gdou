package com.zhifa.gdou.controller;

import com.zhifa.gdou.model.Manager;
import com.zhifa.gdou.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    ManagerService managerService;

    @RequestMapping("/manager_login")
    public Object manager_login(String managerName, String managerPassword, HttpSession httpSession){
        Manager manager = managerService.manager_login(managerName, managerPassword);
        Map<String,Object> map=new HashMap<>();
        if (manager!=null){
            httpSession.setAttribute("manager",manager);
            map.put("status",true);
            map.put("url","manager_main_page");
            return map;
        }
        map.put("status",false);
        map.put("msg","账号或者密码有错！请检查！");
        return map;
    }

    @RequestMapping("/clearSession")
    public String clearSession(HttpSession httpSession){
        httpSession.setAttribute("manager",null);
        return "clearSession";
    }




}
