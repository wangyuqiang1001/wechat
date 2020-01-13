package com.wang.wechat.util.menu;


import com.alibaba.fastjson.JSONObject;
import com.wang.wechat.bean.Wechat;
import com.wang.wechat.bean.menu.Menu;
import com.wang.wechat.util.AccessTokenUtil;
import com.wang.wechat.util.HttpUtils;

import java.util.Map;

public class MenuOperate {


    public static void main(String[] args) {
        Wechat wechat = new Wechat();
        wechat.setAppid("wx99218d55cbece5db");
        wechat.setAppsecret("3181256af427d100573209fded9c619a");
        updMenu(MenuManager.getMenu(wechat), wechat);
    }


    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    public static void updMenu(Menu menu, Wechat wechat) {


        int result = 0;
        // 拼装创建菜单的url
        String accessToken = AccessTokenUtil.getAccessToken(wechat.getAppid(), wechat.getAppsecret());
        System.out.println(accessToken);

        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.toJSONString(menu);
        System.out.println(menu_create_url);
        System.out.println(jsonMenu);

        //这里uri.不是menuCreateURl:
        Map<String, Object> resp = HttpUtils.httpRequest(url, "POST", jsonMenu);
        System.out.println(resp.get("errcode"));
    }
}
