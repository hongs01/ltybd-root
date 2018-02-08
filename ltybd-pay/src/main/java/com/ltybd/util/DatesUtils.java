package com.ltybd.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhouyongbo 2017/11/1
 * 时间工具
 */
public class DatesUtils {
    public final static String FORMAT_13 = "yyyy-MM-dd HH:mm:ss"; //精确到秒
    public final static String FORMAT_8 = "yyyy-MM-dd"; // 年月日
    public final static String FORMAT_17 = "yyyy-MM-dd HH:mm:ss.FFF"; // 精确到毫秒

    public static String format(Date date,String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
}
