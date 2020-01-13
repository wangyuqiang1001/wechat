package com.wang.wechat.bean.menu;

import lombok.Data;

/**
 * 父菜单项 :包含有二级菜单项的一级菜单。这类菜单项包含有二个属性： name和sub_button，而sub_button以是一个子菜单项数组
 *
 * @author Administrator
 */
@Data
public class ComplexButton extends Button {
    private Button[] sub_button;

    private String type;// 菜单点击或者菜单链接目前只有view和click

    private String key;// 若type为click则要给出key值，用于发送对应的推送

    private String url;// 若type为view则给出链接的地址

}
