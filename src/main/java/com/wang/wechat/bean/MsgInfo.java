package com.wang.wechat.bean;

public class MsgInfo {

    public static String appId;

    public static String appSecret;

    public static final String toName = "";

    // 1)消息文本 text
    public static final String TEXT = "text";

    // 2)图片消息 image
    public static final String IMAGE = "image";

    // 3)语音消息 voice
    public static final String VOICE = "voice";

    // 4)视频消息video
    public static final String VIDEO = "video";

    // 5)小视频消息 shortvideo
    public static final String SHORTVIDEO = "shortvideo";

    // 6)地理位置消息 location
    public static final String LOCATION = "location";

    // 7)链接消息
    public static final String LINK = "link";

    // 8）事件推送 event
    public static final String EVENT = "event";

    // a)关注 subscribe
    public static final String SUBSCRIBE = "subscribe";

    // b)取消关注
    public static final String UNSUBSCRIBE = "unsubscribe";

    // c)菜单点击
    public static final String CLICK = "click";

    // d)菜单超链接
    public static final String VIEW = "view";

    // 带参数扫码
    public static final String scancode_waitmsg = "scancode_waitmsg";

    // 扫码推送事件
    public static final String scancode_push = "scancode_push";

    // 系统拍照发图
    public static final String pic_sysphoto = "pic_sysphoto";

    // 拍照或者相册发图
    public static final String pic_photo_or_album = "pic_photo_or_album";

    // 微信相册发图
    public static final String pic_weixin = "pic_weixin";

    // 发送位置
    public static final String location_select = "location_select";

    // 图片
    public static final String media_id = "media_id";

    // 图文消息
    public static final String view_limited = "view_limited";

    public static final String SCAN = "scan";


}
