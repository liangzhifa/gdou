package com.zhifa.gdou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.zhifa.gdou.mapper"})
public class GdouApplication {

    public static void main(String[] args) {


        SpringApplication.run(GdouApplication.class, args);
        System.out.println("======================完成========================");

    }

}

