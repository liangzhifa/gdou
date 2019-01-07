package com.zhifa.gdou.config;

import com.zhifa.gdou.model.Manager;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class LoginConfig implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestURI = httpServletRequest.getRequestURI();
        System.out.println(httpServletRequest.getRequestURI()+"============》preHandle");
       /* HttpSession httpSession = httpServletRequest.getSession();
        Manager manager= (Manager) httpSession.getAttribute("manager");
        if(ObjectUtils.isEmpty(manager)){
            httpServletResponse.sendRedirect("/manager/login");
            return false;

        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        System.out.println("============postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("============afterCompletion");
    }
}
