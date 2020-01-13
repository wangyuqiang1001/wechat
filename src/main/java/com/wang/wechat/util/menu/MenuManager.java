package com.wang.wechat.util.menu;

import com.wang.wechat.bean.WeChatInfo;
import com.wang.wechat.bean.Wechat;
import com.wang.wechat.bean.menu.Button;
import com.wang.wechat.bean.menu.CommonButton;
import com.wang.wechat.bean.menu.ComplexButton;
import com.wang.wechat.bean.menu.Menu;

public class MenuManager {


    public static Menu getMenu(Wechat wechat) {
        String url = "http%3a%2f%2fwww.wangssyuqiang.cn%2fmenu%2fgetOpenId";
        CommonButton buttonLeft1 = new CommonButton();
        buttonLeft1.setName("测试1");
        buttonLeft1.setType("view");
        buttonLeft1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wechat.getAppid()
                + "&redirect_uri=" + url + "&response_type=code&scope=snsapi_userinfo&state=test1#wechat_redirect");
        CommonButton buttonLeft2 = new CommonButton();
        buttonLeft2.setName("测试2");
        buttonLeft2.setType("view");
        buttonLeft2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wechat.getAppid()
                + "&redirect_uri=" + url + "&response_type=code&scope=snsapi_userinfo&state=test2#wechat_redirect");

        ComplexButton complexButton = new ComplexButton();
        complexButton.setName("左侧菜单");
        complexButton.setSub_button(new CommonButton[]{buttonLeft1, buttonLeft2});

        CommonButton buttonRight1 = new CommonButton();
        buttonRight1.setName("右边1");
        buttonRight1.setType("view");
        buttonRight1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + wechat.getAppid()
                + "&redirect_uri=" + url + "&response_type=code&scope=snsapi_userinfo&state=right1#wechat_redirect");

        ComplexButton complexButton2 = new ComplexButton();
        complexButton2.setName("右侧菜单");
        complexButton2.setSub_button(new CommonButton[]{buttonRight1});
        Menu menu = new Menu();
        menu.setButton(new Button[]{complexButton, complexButton2});
        return menu;
    }
}
