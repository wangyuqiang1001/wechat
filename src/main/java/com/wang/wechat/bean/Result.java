package com.wang.wechat.bean;

import com.alibaba.fastjson.JSON;

public class Result<T> {

    public Result() {
    }

    public Result(int status, String message, T object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    // 1成功  2失败  200成功 401未验证或无jwt 403权限受限 400 Bad Request
    public int status;

    // 返回消息
    private String message;

    // 返回数据
    public T object;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
