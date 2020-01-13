package com.wang.wechat.bean.menu;

import lombok.Data;

/**
 * 菜单基类 name
 *
 * @author Administrator
 */
@Data
public class Button {
    private String name;// 所有一级菜单、二级菜单都共有一个相同的属性，那就是name
}
