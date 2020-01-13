package com.wang.wechat.serviceImp;

import com.wang.wechat.bean.Result;
import com.wang.wechat.dao.WeChatInfoMapper;
import com.wang.wechat.service.WeChatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public Result test() {
        Result result = new Result();
        result.setStatus(1);
        result.setMessage("测试成功信息");
        return result;
    }
}
