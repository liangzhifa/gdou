package com.zhifa.gdou.controller;

import com.zhifa.gdou.entity.WxConnect;
import com.zhifa.gdou.service.WxService;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@RestController
public class WxConnectController {

    /**
        bbaa76534524bfe6
     *  http://zhifa.free.idcfengye.com
     * @return
     */
    @RequestMapping(value = "/connect",method = RequestMethod.GET)
    public String connect(WxConnect wxConnect){

        if (WxService.check(wxConnect.getTimestamp(),wxConnect.getNonce(),wxConnect.getSignature())){
            System.out.println("接入成功");
            return wxConnect.getEchostr();
        }
        return "";
    }
    @RequestMapping(value = "/connect",method = RequestMethod.POST)
    public void getMsg(HttpServletRequest request) {

        Map<String, String> requestMap = null;
        try {
            requestMap = WxService.parseRequest(request.getInputStream());
            System.out.println(requestMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
