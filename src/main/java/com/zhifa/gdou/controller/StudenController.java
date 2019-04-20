package com.zhifa.gdou.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mxixm.fastboot.weixin.util.WxWebUtils;
import com.mxixm.fastboot.weixin.web.WxWebUser;
import com.zhifa.gdou.mapper.ClassInfoMapper;
import com.zhifa.gdou.mapper.LeavingMessageMapper;
import com.zhifa.gdou.mapper.ScoreSheetMapper;
import com.zhifa.gdou.mapper.StudentInfoMapper;
import com.zhifa.gdou.model.LeavingMessage;
import com.zhifa.gdou.model.ScoreSheet;
import com.zhifa.gdou.model.StudentInfo;
import com.zhifa.gdou.resultEntity.ScoreVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class StudenController {

    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Autowired
    private ScoreSheetMapper scoreSheetMapper;

    @Autowired
    private  ClassInfoMapper classInfoMapper;

    @Autowired
    private LeavingMessageMapper leavingMessageMapper;

    private static Logger log = LoggerFactory.getLogger(StudenController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));//true:允许输入空值，false:不能为空值
    }

    /**
     * 进去主页面  获取当前用户信息
     * @param session
     * @return
     */
    @RequestMapping("/wx/getUserInfo")
    @ResponseBody
    public String getUserInfo(HttpSession session){

        /**
         * 已经绑定的前提下，设置session值
         */
        WxWebUser wxWebUser = WxWebUtils.getWxWebUserFromSession();
        String openId = wxWebUser.getOpenId();
        StudentInfo studentInfo = studentInfoMapper.selectByOpenId(openId);
        session.setAttribute("student",studentInfo);
        log.info("OPEN_ID=>{}\t studentInfo=>{}",wxWebUser.getOpenId(),studentInfo.toString());
        return studentInfo.getStudentname();
    }

    /**
     * huoqu考试时间
     * @param session
     * @return
     */
    @RequestMapping("/wx/getTestDate")
    public Object getTestDate(HttpSession session){
        StudentInfo student = (StudentInfo) session.getAttribute("student");
        List<String> dateList = scoreSheetMapper.findTestDate(student.getStudentnum());
        return dateList;
    }

    /**
     * huoqu考试所有考试科目
     * @param session
     * @return
     */
    @RequestMapping("/wx/getCourseAll")
    public Object getCourseAll(HttpSession session){
        StudentInfo student = (StudentInfo) session.getAttribute("student");
        List<String> courseList = scoreSheetMapper.getCourseAll(student.getStudentnum());
        return courseList;
    }



    /**
     * 通过日期查询各门成绩
     * @param session
     * @param testTime
     * @return
     */
    @RequestMapping("/wx/getScoreByDate")
    public Object getScoreByDate(HttpSession session,@RequestParam(value = "testTime",required = false) Date testTime){

        StudentInfo student = (StudentInfo) session.getAttribute("student");
        if (testTime==null){
            testTime= scoreSheetMapper.getNearOneTime(student.getStudentnum());
        }
        List<ScoreSheet> scoreSheets = scoreSheetMapper.findByNumAndTime(student.getStudentnum(), testTime);
        JSONObject jsonObject = new JSONObject();
        Map<String,Object>map=new HashMap<>();
        List<ScoreVo> data=new LinkedList<>();
        JSONArray jsonArray=new JSONArray();
        JSONObject json;
        for (int i = 0; i < scoreSheets.size(); i++) {
            ScoreSheet scoreSheet = scoreSheets.get(i);
            map.put(scoreSheet.getCourse(),scoreSheet.getScore());
            data.add(new ScoreVo(scoreSheet.getCourse(),(scoreSheet.getScore()/100.0)));
            json=new JSONObject();
            json.put("name",scoreSheet.getCourse());
            json.put("y",scoreSheet.getScore());
            json.put("const","const");
            jsonArray.add(json);
        }
        jsonObject.put("map",map);
        jsonObject.put("data",data);
        jsonObject.put("data3",jsonArray);
        return jsonObject;


    }

    /**
     * chakan最近半年成绩
     * @param session
     * @return
     */
    @RequestMapping("/wx/getScoreAll")
    public Object getScoreAll(HttpSession session){
        StudentInfo student = (StudentInfo) session.getAttribute("student");

        List<Date> dates = scoreSheetMapper.findTestDateFormat(student.getStudentnum());
        JSONObject jsonres = new JSONObject();
        JSONArray jsonArray=new JSONArray();
        JSONArray data2=new JSONArray();
        JSONObject json;
        JSONObject json2;
        for (int i = 0; i < dates.size(); i++) {
            List<ScoreSheet> scoreSheets = scoreSheetMapper.findByNumAndTime(student.getStudentnum(), dates.get(i));
            for (int k = 0; k < scoreSheets.size(); k++) {
                ScoreSheet scoreSheet = scoreSheets.get(k);
                json=new JSONObject();
                json2=new JSONObject();
                json.put("date", new SimpleDateFormat("yyyy-MM-dd").format(scoreSheet.getTestTime()));
                json.put("type",scoreSheet.getCourse());
                json.put("value",scoreSheet.getScore());
                json2.put("item", scoreSheet.getCourse());
                json2.put("user",new SimpleDateFormat("yyyy-MM-dd").format(scoreSheet.getTestTime()));
                json2.put("score", scoreSheet.getScore());
                jsonArray.add(json);
                data2.add(json2);
            }
        }
        jsonres.put("data1",jsonArray);
        jsonres.put("data2",data2);

        return jsonres;
    }

    /**
     * 查询该学生历年的单科成绩
     * @param session
     * @return
     */
    @RequestMapping("/wx/getScoreByCourse")
    public Object getScoreByCourse(HttpSession session,String course ){
        StudentInfo student = (StudentInfo) session.getAttribute("student");
        if (StringUtils.isEmpty(course)){
            course = scoreSheetMapper.getCourseAllLimit1(student.getStudentnum());
        }
        List<ScoreSheet> scoreSheetList = scoreSheetMapper.getScoreByCourse(student.getStudentnum(), course);
        JSONArray data = new JSONArray();
        ArrayList<String> ticks = new ArrayList<>();


        for (int i = 0; i < scoreSheetList.size(); i++) {
            JSONObject  temp = new JSONObject();
            ScoreSheet scoreSheet = scoreSheetList.get(i);
            temp.put("date", new SimpleDateFormat("dd/MM/yyyy").format(scoreSheet.getTestTime()));
            temp.put("score", scoreSheet.getScore());
            data.add(temp);
        }
        if (scoreSheetList.size()<=3){
            for (int i = 0; i <scoreSheetList.size() ; i++) {
                ticks.add(new SimpleDateFormat("dd/MM/yyyy").format(scoreSheetList.get(i).getTestTime()));
            }
        }else {
            ticks.add(new SimpleDateFormat("dd/MM/yyyy").format(scoreSheetList.get(0).getTestTime()));
            ticks.add(new SimpleDateFormat("dd/MM/yyyy").format(scoreSheetList.get(scoreSheetList.size()/2).getTestTime()));
            ticks.add(new SimpleDateFormat("dd/MM/yyyy").format(scoreSheetList.get(scoreSheetList.size()-1).getTestTime()));
        }
        JSONObject jsonres = new JSONObject();
        jsonres.put("data",data);
        jsonres.put("ticks",ticks);
        return jsonres;
    }
    /**
     * @Author: zhifa
     * @Date: 2019/4/21
     * @Description: 根据学生查询班主任所有留言
     */
    @RequestMapping("/wx/getLeavingMsg")
    public Object getLeavingMsg(HttpSession session){
        StudentInfo student = (StudentInfo) session.getAttribute("student");
        String teacherNum = classInfoMapper.findTeacherNumByStuNum(student.getStudentnum());
        List<LeavingMessage> messages = leavingMessageMapper.getTeacherLeavingContent(teacherNum);
        return messages;
    }






}
