package com.wang.wechat.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.net.www.http.HttpClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class AccessTokenUtil {
    private static String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";


    //每次返回的时间会显示一个过期时间:需要作超时设置:
    private static ConcurrentHashMap tokenMap = new ConcurrentHashMap<String, Object>();


    /**
     * 当需要刷新时刷新token:
     *
     * @return
     */
    public static String getAccessToken(String appId, String appSecret) {
        boolean flag = tokenMap.get("token") == null;
        String requestUrl = tokenUrl.replaceFirst("APPID", appId).replace("APPSECRET", appSecret);
        if (flag || (Long) tokenMap.get("expiresIn") > System.currentTimeMillis()) { //初始化.或者过期刷新:
            Map<String, Object> resp = HttpUtils.httpGet(requestUrl);
            if (resp.get("errcode") == null) {
                tokenMap.put("token", (String) resp.get("access_token"));
                tokenMap.put("expiresIn", System.currentTimeMillis() + (Integer) resp.get("expires_in")); //截止时间:
            } else {
                log.info("获取token错误，错误代码: {}", resp.get("errcode"));
            }
        }

        return (String) tokenMap.get("token");
    }
}
