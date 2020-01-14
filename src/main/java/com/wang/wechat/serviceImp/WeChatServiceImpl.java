package com.wang.wechat.serviceImp;

import com.wang.wechat.bean.Message;
import com.wang.wechat.bean.MsgInfo;
import com.wang.wechat.bean.Result;
import com.wang.wechat.dao.WeChatInfoMapper;
import com.wang.wechat.service.WeChatService;
import com.wang.wechat.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WeChatServiceImpl implements WeChatService {

    @Resource
    private WeChatInfoMapper weChatInfoMapper;

    @Value("${wechat.token}")
    private String token;


    /**
     * 微信服务器验证，将，token,timestamp, nonce,参数按照字典排序，然后对字符串进行sha1加密:
     *
     * @return
     */
    @Override
    public String verifyToken(String signature, String timestamp, String nonce, String echoStr) {
        List<String> list = Arrays.asList(new String[]{timestamp, nonce, token});
        Collections.sort(list);
        StringBuffer checkStr = new StringBuffer();
        list.stream().forEach(l -> {
            checkStr.append(l);
        });
        String pswStr = DigestUtils.sha1Hex(checkStr.toString());
        log.info("hexStr: {}, signature: {}", pswStr, signature);
        return signature.equals(pswStr) ? echoStr : "fail";
    }


    @Override
    public void echoMsg(HttpServletRequest request, HttpServletResponse response) {

        try {
            PrintWriter writer = response.getWriter();
            Map<String, String> xmlMap = null;
            InputStream ins = null;
            //1: 将输入流中的xml数据解析成map
            ins = request.getInputStream();
            Map<String, String> messageMap = MessageUtil.toMap(ins);
            ins.close();
            //2:将map数据封装成message对象：
            Message message = MessageUtil.toMessage(messageMap);

            //在五秒钟内对消息作出回复:更改发送方和接收方信息:
            String fromUserName = message.getFromUserName();
            String toUserName = message.getToUserName();
            message.setFromUserName(toUserName);
            message.setToUserName(fromUserName);
            message.setCreateTime(System.currentTimeMillis());

            String msgType = message.getMsgType(); //消息类型：
            String reply = "";
            if (MsgInfo.TEXT.equals(msgType)) {
                reply = "回复的文本信息";
            } else if (MsgInfo.IMAGE.equals(msgType)) {
                reply = " 回复的图片信息";
            }
            message.setContent(reply);

            String xmlStr = MessageUtil.toXml(message);
            writer.write(xmlStr);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Result test() {
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("测试成功信息");
        return result;
    }
}
