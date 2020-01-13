package com.wang.wechat.bean;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Date;

/**
 * wechat信息实体类
 *
 * @author wb
 */

@Data
public class Wechat {
    /**
     *
     */
    private Integer wechatid;
    /**
     *
     */
    private Integer userid;
    /**
     *
     */
    private Integer cjoyid;
    /**
     * 公众号名称
     */
    private String wechatname;
    /**
     * appid、appsecret申请公共号中，获取的appid、appsecret
     */
    private String appid;
    private String appsecret;
    /**
     * 商户id
     */
    private String mchid;
    /**
     * 商户apikey ,调用支付接口时需要用到
     */
    private String apikey;
    /**
     * 口令，第一次配置公众号合法服务器时，需要此验证
     */
    private String token;
    /**
     *
     */
    private String encondingasekey;
    /**
     *
     */
    private String encodingstyle;
    /**
     * 状态：暂未用，之后用于暂停管理
     */
    private Integer status;
    /**
     * 生成时间
     */
    private Date createtime;
    /**
     * 用户第一次关注时的欢迎语
     */
    private String welcomemsg;
    /**
     * 充值提醒的模板id
     */
    private String notifyrecharge;
    /**
     * 余额不足提醒的模板id
     */
    private String notifynomoney;
    /**
     *
     */
    private String notifybalance;
    /**
     * 退款提醒的模板id
     */
    private String notifyrefund;

    private String reserved2;

    private String reserved3;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
