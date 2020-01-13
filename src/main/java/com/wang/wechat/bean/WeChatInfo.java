package com.wang.wechat.bean;

import lombok.Data;

import java.util.Date;

@Data
public class WeChatInfo {
    private Integer id;
    private String openId;
    private Date insTime;
    private Date updTime;
}



