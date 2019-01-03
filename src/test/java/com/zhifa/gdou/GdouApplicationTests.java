package com.zhifa.gdou;

import com.zhifa.gdou.mapper.ManagerMapper;
import com.zhifa.gdou.model.Manager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GdouApplicationTests {

    @Autowired
    ManagerMapper managerMapper;

    @Test
    public void contextLoads() {
        List<Manager> managers = managerMapper.findAll();
        System.out.println(managers);
    }

}

