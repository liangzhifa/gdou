package com.zhifa.gdou.service;


import com.zhifa.gdou.mapper.ScoreSheetMapper;
import com.zhifa.gdou.model.ScoreSheet;
import com.zhifa.gdou.utils.ScoreUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreTest {
    @Autowired
    private ScoreSheetMapper scoreSheetMapper;



    @Test
    public void f() throws ParseException {
        List<Date> allTestDate = new LinkedList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        allTestDate.add(df.parse("2017-1-19"));
        allTestDate.add(df.parse("2017-2-16"));
        allTestDate.add(df.parse("2017-3-12"));
        allTestDate.add(df.parse("2017-4-16"));
        allTestDate.add(df.parse("2017-5-20"));
        allTestDate.add(df.parse("2017-9-23"));
        allTestDate.add(df.parse("2017-10-12"));

        allTestDate.add(df.parse("2018-1-19"));
        allTestDate.add(df.parse("2018-2-16"));
        allTestDate.add(df.parse("2018-3-12"));
        allTestDate.add(df.parse("2018-4-16"));
        allTestDate.add(df.parse("2018-5-20"));
        allTestDate.add(df.parse("2018-9-23"));
        allTestDate.add(df.parse("2018-10-12"));

        allTestDate.add(df.parse("2019-1-19"));
        allTestDate.add(df.parse("2019-2-16"));
        allTestDate.add(df.parse("2019-3-12"));
        allTestDate.add(df.parse("2019-4-16"));
        allTestDate.add(df.parse("2019-5-20"));


        String stu[] = {"2015","2016","2017","2018"};
        for (int k = 0; k < stu.length; k++) {
            for (int i = 0; i < allTestDate.size(); i++) {
                Date testTime = allTestDate.get(i);
                Math.random();
                Integer yuwen = Integer.valueOf((int) Math.round(Math.random() * 50 + 60));
                Integer shuxue = Integer.valueOf((int) Math.round(Math.random() * 50 + 60));
                Integer yingyu = Integer.valueOf((int) Math.round(Math.random() * 50 + 60));
                Integer wuli = Integer.valueOf((int) Math.round(Math.random() * 40 + 60));
                Integer huaxue = Integer.valueOf((int) Math.round(Math.random() * 40 + 60));
                Integer shengwu = Integer.valueOf((int) Math.round(Math.random() * 40 + 60));
                ScoreSheet scoreSheet1=new ScoreSheet(stu[k], ScoreUtil.YUWEN,yuwen,testTime);
                scoreSheetMapper.insert(scoreSheet1);
                ScoreSheet scoreSheet2=new ScoreSheet(stu[k], ScoreUtil.SHUXUE,shuxue,testTime);
                scoreSheetMapper.insert(scoreSheet2);
                ScoreSheet scoreSheet3=new ScoreSheet(stu[k], ScoreUtil.YINGYU,yingyu,testTime);
                scoreSheetMapper.insert(scoreSheet3);
                ScoreSheet scoreSheet4=new ScoreSheet(stu[k], ScoreUtil.WULI,wuli,testTime);
                scoreSheetMapper.insert(scoreSheet4);
                ScoreSheet scoreSheet5=new ScoreSheet(stu[k], ScoreUtil.HUAXUE,huaxue,testTime);
                scoreSheetMapper.insert(scoreSheet5);
                ScoreSheet scoreSheet6=new ScoreSheet(stu[k], ScoreUtil.SHENGWU,shengwu,testTime);
                scoreSheetMapper.insert(scoreSheet6);
            }
        }



    }

}
