package com.zhifa.gdou.service;

import com.zhifa.gdou.mapper.ManagerMapper;
import com.zhifa.gdou.model.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    @Autowired
    ManagerMapper managerMapper;

    public Manager manager_login(String managerName, String managerPassword){
        Manager manager = managerMapper.findByNameAndPassword(managerName, managerPassword);
        return  manager;
    }

}
