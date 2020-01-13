package com.wang.wechat.service;

import com.wang.wechat.bean.Result;

public interface WeChatService {
    Result test();

    String verifyToken(String signature, String timestamp, String nonce, String echoStr);
}
