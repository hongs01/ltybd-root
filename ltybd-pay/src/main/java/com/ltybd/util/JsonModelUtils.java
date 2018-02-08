package com.ltybd.util;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author zhouyongbo 2017/10/31
 * json对象转换
 */
public class JsonModelUtils {

   private static Logger logger = LoggerFactory.getLogger(JsonModelUtils.class);

    /**
     * 将对象转换为字符串类型
     * @param obj
     * @return
     */
    public static String jsonModle(Object obj){
        try {
            return JSON.json(obj).toString();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("JsonModelUtils:"+e.getMessage());
            return null;
        }
    }

    /**
     * 将字符串转换为对象模型
     * @param jsonModel
     * @param <T>
     * @return
     * @throws ParseException
     */
    public static <T> T pareModle(String jsonModel,Class<T> tClass) throws ParseException {
        return JSON.parse(jsonModel,tClass);
    }
}
