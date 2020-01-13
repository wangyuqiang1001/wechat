package com.wang.wechat.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {


    @RequestMapping(value = "/getOpenId")
    public String getOpenId(HttpServletRequest request, Map<String, Object> param) {
        return "";
    }
}
