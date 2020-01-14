package com.wang.wechat.bean;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * 发送消息实体类
 * 
 * @author wb
 *
 */
@Data
public class Message {
	/**
	 * 开发者微信号
	 */
	private String ToUserName;
	/**
	 * 关注人
	 */
	private String FromUserName;
	/**
	 * 消息创建时间
	 */

	private long CreateTime;
	/**
	 * 消息类型
	 */
	private String MsgType;
	/**
	 * 内容
	 */

	private String Content;
	/**
	 * 消息id
	 */
	private String MsgId;

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
