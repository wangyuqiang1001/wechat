package com.wang.wechat.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 其实是用户详细信息获取access_token, 包含openId:
 */
public class OpenIdUtil {

    /**
     * @param code      从合法域名跳转得到的code
     * @param appId     每个公众号对应的appId
     * @param appSecret 每个公众号对应的appSecret
     * @return
     */
    public static String getOpenid(String code, String appId, String appSecret) {

        String openId = null;
        if (code == null) {
        } else {

            // 接口url,拼接好参数
            String url = " https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
            url = url.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);

            // 调用接口
            Map<String, Object> resp = HttpUtils.httpGet(url);
            try {
                openId = (String) resp.get("openid");
            } catch (Exception e) {
                // TODO: handle exception
            }

        }
        return openId;
    }
}
