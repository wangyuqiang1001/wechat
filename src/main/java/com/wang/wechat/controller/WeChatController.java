package com.wang.wechat.controller;

import com.wang.wechat.bean.Result;
import com.wang.wechat.service.WeChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wechat")
public class WeChatController {


    @Resource
    private WeChatService wechatService;


    /**
     * 微信服务器信息验证
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/connection", method = RequestMethod.GET)
    public String connection(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        return wechatService.verifyToken(signature, timestamp, nonce, echostr);
    }

    @RequestMapping(value = "/test")
    public Result test() {
        return wechatService.test();
    }
}
