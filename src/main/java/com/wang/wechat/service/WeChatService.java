package com.wang.wechat.service;

import com.wang.wechat.bean.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WeChatService {
    Result test();


    void echoMsg(HttpServletRequest request, HttpServletResponse response);

    String verifyToken(String signature, String timestamp, String nonce, String echoStr);
}
