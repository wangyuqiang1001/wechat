package com.wang.wechat.bean.menu;

import lombok.Data;

/**
 * 底层菜单的封装 包涵所有菜单属性 需要那种类型的菜单就根据相应类型set参数
 *
 * @author Administrator
 */

@Data
public class CommonButton extends Button {

    private String type;// 菜单点击或者菜单链接目前只有view和click
    private String key;// 若type为click则要给出key值，用于发送对应的推送
    private String url;// 若type为view则给出链接的地址
    // private String media_id;//发送永久素材时有用
}
