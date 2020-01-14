package com.wang.wechat.controller;


import com.wang.wechat.util.OpenIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequestMapping("/menu")
@Controller
@Slf4j
public class MenuController {


    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.appSecret}")
    private String appSecret;


    @RequestMapping(value = "/getOpenId")
    public String getOpenId(HttpServletRequest request, Map<String, Object> param) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        log.info("当前点击的菜单是: {}", state);
        HttpSession session = request.getSession();
        String openId = "";
        if (session.getAttribute("openId") != null) {
            openId = (String) session.getAttribute("openId");
        } else {
            openId = OpenIdUtil.getOpenid(code, appId, appSecret);
        }
        log.info("state: {}, openId: {}", state, openId);
        param.put("code", state);
        return state;
    }
}
