package com.zhifa.gdou.config;

import com.zhifa.gdou.model.Manager;
import com.zhifa.gdou.model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoginConfig implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(LoginConfig.class);
    private static List<String> managerUrl = new ArrayList<>();
    private static List<String> teacherUrl = new ArrayList<>();


    @Override

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestURI = httpServletRequest.getRequestURI();
        HttpSession session = httpServletRequest.getSession();
        Manager manager = (Manager) session.getAttribute("manager");
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (ObjectUtils.isEmpty(manager)) {
            if (managerUrl.contains(requestURI)) {
                log.info("过滤 管理员url=>requestURI={}", requestURI);
                return false;
            }
        }
        if (ObjectUtils.isEmpty(teacher)) {
            if (teacherUrl.contains(requestURI)) {
                log.info("过滤 教师url=>requestURI={}", requestURI);
                return false;
            }
        }


        log.info("requestURI={}", requestURI);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        // System.out.println("============postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // System.out.println("============afterCompletion");
    }

    static {
        managerUrl.add("/teacher/findAllTeachers");
        managerUrl.add("/classInfo/findAllclassInfos");
        managerUrl.add("/student/findAllStudents");
        managerUrl.add("/wxMoreInfo/findAllWxMoreInfos");
        managerUrl.add("/teacher/deleteById");
        managerUrl.add("/teacher/insertTeacherInfo");
        managerUrl.add("/teacher/modify");
        managerUrl.add("/upload/studentInfos");
        //=============================
        teacherUrl.add("/student/getScore");
        teacherUrl.add("/student/insertscore");

    }
}
