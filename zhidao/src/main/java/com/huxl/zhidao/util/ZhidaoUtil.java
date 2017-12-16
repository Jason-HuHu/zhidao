package com.huxl.zhidao.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @author Jasonhu
 * @Date 2017-12-11
 * tool class
 */
public class ZhidaoUtil {
    private static final Logger logger = LoggerFactory.getLogger(ZhidaoUtil.class);
    public static int ANOYMOUS_USERID = 111111;
    public static String getJSONString (int code) {
        JSONObject json = new JSONObject();
        json.put("code",code);
        return json.toJSONString();
    }
    public static String getJSONString (int code,String msg) {
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        return json.toJSONString();
    }
    /**
     * MD5加密函数
     * @param key
     * @return
     */
    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            //获得MD5摘要算法的MessageDigest摘要对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            mdInst.update(btInput);
            //获取密文
            byte[] md = mdInst.digest();
            //把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);

        }catch (Exception e) {
            logger.error("生成MD5失败",e);
            return null;
        }
    }

}
