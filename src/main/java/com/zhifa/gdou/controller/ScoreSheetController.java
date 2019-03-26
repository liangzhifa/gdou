package com.zhifa.gdou.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhifa.gdou.mapper.ClassInfoMapper;
import com.zhifa.gdou.mapper.ScoreSheetMapper;
import com.zhifa.gdou.mapper.StudentInfoMapper;
import com.zhifa.gdou.model.ScoreSheet;
import com.zhifa.gdou.model.Teacher;
import com.zhifa.gdou.resultEntity.LayUIDataGrid;
import com.zhifa.gdou.resultEntity.ScoreResultEntity;
import com.zhifa.gdou.utils.ExcelUtil;
import com.zhifa.gdou.utils.ScoreTempEntity;
import com.zhifa.gdou.utils.ScoreUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ScoreSheetController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
    }

    @Autowired
    private ScoreSheetMapper scoreSheetMapper;

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Autowired
    private ClassInfoMapper classInfoMapper;

    @RequestMapping(value = "/upload/studentScore")
    @ResponseBody
    public Object uploadstudentScore(@RequestParam(value = "excel") MultipartFile excel,
                                     HttpServletRequest request,
                                     HttpServletResponse response,
                                     HttpSession httpSession) {
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        Map<Object,Object>map=new HashMap<>();
        if (teacher==null){
            map.put("code",1);
            map.put("msg","请用教师账号登录");
            return map;
        }
        // 错误信息  （可以定义一个结果类用于保存信息-错误个数，正确个数，错误信息等）
        ArrayList<String> errors = new ArrayList<String>();

        try {
            if (!excel.isEmpty() && excel.getOriginalFilename().endsWith("xls")) {
                // poi--exl解析
                InputStream is = excel.getInputStream();
                HSSFWorkbook hssf = new HSSFWorkbook(is);
                //Sheet1-----是Excel表格左下方的名称
                HSSFSheet sheet = hssf.getSheet("Sheet1");

                //读取Excel表格从下标0开始（表头开始）

                String stuNumHead = ExcelUtil.getHSSFCell(sheet, 0, 0).toString();
                String stuNameHead = ExcelUtil.getHSSFCell(sheet, 0, 1).toString();
                String testTimeHead = ExcelUtil.getHSSFCell(sheet, 0, 2).toString();
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                    String stuNum = sheet.getRow(i).getCell(0).toString();
                    String stuName=sheet.getRow(i).getCell(1).toString();
                    Date testTime = sheet.getRow(i).getCell(2).getDateCellValue();
                    HSSFCell key1 = ExcelUtil.getHSSFCell(sheet, 0, 3);
                    if (key1!=null){
                        String k1  = key1.toString();
                        double v1 = ExcelUtil.getHSSFCell(sheet, i, 3).getNumericCellValue();
                        ScoreSheet scoreSheet=new ScoreSheet(stuNum,k1,(int) v1,testTime);
                        scoreSheetMapper.insert(scoreSheet);
                    }
                    HSSFCell key2 = ExcelUtil.getHSSFCell(sheet, 0, 4);
                    if (key2!=null){
                        String k1  = key2.toString();
                        double v1 = ExcelUtil.getHSSFCell(sheet, i, 4).getNumericCellValue();
                        ScoreSheet scoreSheet=new ScoreSheet(stuNum,k1,(int) v1,testTime);
                        scoreSheetMapper.insert(scoreSheet);
                    }
                    HSSFCell key3 = ExcelUtil.getHSSFCell(sheet, 0, 5);
                    if (key3!=null){
                        String k1  = key3.toString();
                        double v1 = ExcelUtil.getHSSFCell(sheet, i, 5).getNumericCellValue();
                        ScoreSheet scoreSheet=new ScoreSheet(stuNum,k1,(int) v1,testTime);
                        scoreSheetMapper.insert(scoreSheet);
                    }
                    HSSFCell key4 = ExcelUtil.getHSSFCell(sheet, 0, 6);
                    if (key4!=null){
                        String k1  = key4.toString();
                        double v1 = ExcelUtil.getHSSFCell(sheet, i, 6).getNumericCellValue();
                        ScoreSheet scoreSheet=new ScoreSheet(stuNum,k1,(int) v1,testTime);
                        scoreSheetMapper.insert(scoreSheet);
                    }
                    HSSFCell key5 = ExcelUtil.getHSSFCell(sheet, 0, 7);
                    if (key3!=null){
                        String k1  = key5.toString();
                        double v1 = ExcelUtil.getHSSFCell(sheet, i, 7).getNumericCellValue();

                        ScoreSheet scoreSheet=new ScoreSheet(stuNum,k1, (int) v1,testTime);
                        scoreSheetMapper.insert(scoreSheet);
                    }
                    HSSFCell key6 = ExcelUtil.getHSSFCell(sheet, 0, 8);
                    if (key3!=null){
                        String k1  = key6.toString();
                        double v1 = ExcelUtil.getHSSFCell(sheet, i, 8).getNumericCellValue();
                        ScoreSheet scoreSheet=new ScoreSheet(stuNum,k1,(int) v1,testTime);
                        scoreSheetMapper.insert(scoreSheet);
                    }

                }
                System.out.println("============上传完毕===========");
            }
        } catch (IOException e) {
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","上传出现错误，请重试");
            return map;
        }
        map.put("code",0);
        map.put("msg","上传成功！");
        return map;
    }


    @RequestMapping("/student/insertscore")
    public Object studentInsertscore(Date testTime ,String studentNum,Integer yuwen
            ,Integer shuxue,Integer yingyu,Integer wuli,Integer huaxue,Integer shengwu){
        Map<Object,Object>map=new HashMap<>();
        ScoreSheet scoreSheet1=new ScoreSheet(studentNum, ScoreUtil.YUWEN,yuwen,testTime);
        scoreSheetMapper.insert(scoreSheet1);
        ScoreSheet scoreSheet2=new ScoreSheet(studentNum, ScoreUtil.SHUXUE,shuxue,testTime);
        scoreSheetMapper.insert(scoreSheet2);
        ScoreSheet scoreSheet3=new ScoreSheet(studentNum, ScoreUtil.YINGYU,yingyu,testTime);
        scoreSheetMapper.insert(scoreSheet3);
        ScoreSheet scoreSheet4=new ScoreSheet(studentNum, ScoreUtil.WULI,wuli,testTime);
        scoreSheetMapper.insert(scoreSheet4);
        ScoreSheet scoreSheet5=new ScoreSheet(studentNum, ScoreUtil.HUAXUE,huaxue,testTime);
        scoreSheetMapper.insert(scoreSheet5);
        ScoreSheet scoreSheet6=new ScoreSheet(studentNum, ScoreUtil.SHENGWU,shengwu,testTime);
        scoreSheetMapper.insert(scoreSheet6);
        map.put("code",0);
        map.put("msg","录入成功");
        return map;
    }

    @RequestMapping("/student/getScore")
    public Object studentGetScore(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit",defaultValue = "40")Integer limit,
                                  @RequestParam(value = "testTime",required = false) Date testTime,
                                  HttpSession httpSession){
        Teacher teacher = (Teacher) httpSession.getAttribute("teacher");
        if (teacher==null){
            return LayUIDataGrid.ReturnDataGrid(0,new ArrayList());
        }

        String teacherNum = teacher.getTeacherNum();
        List<String> stuNums = classInfoMapper.findStuNum(teacherNum);

        if (ObjectUtils.isEmpty(testTime)){//前端不传日期则显示最新的成绩数据
            testTime=scoreSheetMapper.getNewTestDate();
        }
        PageHelper.startPage(page, limit);//分页
        List<ScoreTempEntity> groupByTestTime = scoreSheetMapper.getGroupByTestTime(testTime,stuNums);
        PageInfo<ScoreTempEntity> pageInfo=new PageInfo(groupByTestTime);
        long total = pageInfo.getTotal();
        List<ScoreTempEntity> pageInfoList = pageInfo.getList();
        List<ScoreResultEntity> res=new ArrayList<>(40);
        for (ScoreTempEntity s:pageInfoList) {
            List<ScoreSheet> scoreSheets = scoreSheetMapper.findByNumAndTime(s.getStudentnum(), s.getTestTime());
            ScoreResultEntity scoreResultEntity=new ScoreResultEntity();
            scoreResultEntity.setStudentnum(s.getStudentnum());
            String stuName = studentInfoMapper.findNameByNum(s.getStudentnum());
            scoreResultEntity.setStudentname(stuName);
            scoreResultEntity.setTestTime(s.getTestTime());
            int sum=0;
            for (ScoreSheet score:scoreSheets){
                sum+=score.getScore();
                if (score.getCourse().equals("语文")){
                    scoreResultEntity.setYuwen(score.getScore());
                }
                if (score.getCourse().equals("数学")){
                    scoreResultEntity.setShuxue(score.getScore());
                }
                if (score.getCourse().equals("英语")){
                    scoreResultEntity.setYingyu(score.getScore());
                }
                if (score.getCourse().equals("物理")){
                    scoreResultEntity.setWuli(score.getScore());

                }
                if (score.getCourse().equals("化学")){
                    scoreResultEntity.setHuaxue(score.getScore());
                }
                if (score.getCourse().equals("生物")){
                    scoreResultEntity.setShengwu(score.getScore());
                }
            }
            scoreResultEntity.setZongfen(sum);
            res.add(scoreResultEntity);
        }
        return LayUIDataGrid.ReturnDataGrid(total,res);
    }

    @RequestMapping("/student/modify")
    public Object studentModify(Date testTime,String studentNum,
                                Integer yuwen,Integer shuxue,Integer yingyu,
                                Integer wuli,Integer huaxue,Integer shengwu){
        /*
        * "testTime":"2019-05-13","studentNum":"2018",
        * "yuwen":"99","shuxue":"98","yingyu":"90",
        * "wuli":"90","huaxue":"90","shengwu":"90"
        * */
        Map<Object,Object>map=new HashMap<>();
        scoreSheetMapper.updateScoreByNumAndCorseAndTestTime(testTime,studentNum,"语文",yuwen);
        scoreSheetMapper.updateScoreByNumAndCorseAndTestTime(testTime,studentNum,"数学",shuxue);
        scoreSheetMapper.updateScoreByNumAndCorseAndTestTime(testTime,studentNum,"英语",yingyu);
        scoreSheetMapper.updateScoreByNumAndCorseAndTestTime(testTime,studentNum,"物理",wuli);
        scoreSheetMapper.updateScoreByNumAndCorseAndTestTime(testTime,studentNum,"化学",huaxue);
        scoreSheetMapper.updateScoreByNumAndCorseAndTestTime(testTime,studentNum,"生物",shengwu);
        map.put("code",0);
        map.put("msg","修改成功");
        return map;
    }

}
